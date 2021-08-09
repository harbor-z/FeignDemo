package com.example.demo.config;

import org.springframework.context.annotation.Configuration;

import com.example.demo.annotation.MyHttpRequestScanner;


/**
 * @author zhougang <zhougang@kuaishou.com>
 * Created on 2020-09-01
 */
@MyHttpRequestScanner(value = "com.example.demo.service.openapi")
@Configuration
public class MyHttpRequestConfig {
}
