package com.example.demo.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhougang <zhougang@kuaishou.com>
 * Created on 2020-09-01
 */
@Slf4j
public class RestTemplateUtil {

    private static class SingletonRestTemplate {
        static final RestTemplate INSTANCE = new RestTemplate();
    }

    private RestTemplateUtil() {
    }


    public static RestTemplate getInstance() {
        return SingletonRestTemplate.INSTANCE;
    }

    public static String post(String url, String data) {
        log.info("url:{}", url);
        log.info("request:{}", data);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-Encoding", "UTF-8");
        headers.add("Content-Type", "application/json; charset=UTF-8");

        HttpEntity<String> requestEntity = new HttpEntity<String>(data, headers);
        String response = RestTemplateUtil.getInstance().postForObject(url, requestEntity, String.class);
        log.info("response:{}", response);
        return response;
    }

    public static String get(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-Encoding", "UTF-8");
        headers.add("Content-Type", "application/json; charset=UTF-8");

        HttpEntity<String> requestEntity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response =
                RestTemplateUtil.getInstance().exchange(url, HttpMethod.GET, requestEntity, String.class);
        String responseBody = response.getBody();
        return responseBody;
    }
}
