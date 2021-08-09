package com.example.demo.service.openapi;

import com.example.demo.annotation.MyHttpRequest;
import com.example.demo.annotation.PostRequest;
import com.example.demo.model.TestRequestBody;

/**
 * @author zhougang <zhougang@kuaishou.com>
 * Created on 2020-08-29
 */
@MyHttpRequest
public interface TestHttpRequestService {

    @PostRequest(url = "https://op-vc-open.test.gifshow.com/openapi/trade/item/modify")
    String testPostRequest(TestRequestBody requestBody);
}
