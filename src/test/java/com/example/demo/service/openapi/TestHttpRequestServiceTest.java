package com.example.demo.service.openapi;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.TestRequestBody;

/**
 * @author zhougang <zhougang@kuaishou.com>
 * Created on 2021-08-06
 */
@SpringBootTest
class TestHttpRequestServiceTest {

    @Resource
    private TestHttpRequestService testHttpRequestService;

    @Test
    void testPostRequest() {
        TestRequestBody requestBody = new TestRequestBody();
        requestBody.setName("小快");
        requestBody.setAge("18");
        String request = testHttpRequestService.testPostRequest(requestBody);
        System.out.println(request);
    }
}