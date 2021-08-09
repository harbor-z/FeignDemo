package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.example.demo.service.CommonRequestService;
import com.example.demo.utils.RestTemplateUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhougang <zhougang@kuaishou.com>
 * Created on 2020-09-01
 */
@Service
@Slf4j
public class CommonRequestServiceImpl implements CommonRequestService {

    public CommonRequestServiceImpl() {
    }

    @Override
    public String post(String url, Object requestBody) {
        String requestBodyJsonString = JSON.toJSONString(requestBody);
        return RestTemplateUtil.post(url, requestBodyJsonString);
    }

}
