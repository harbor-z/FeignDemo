package com.example.demo.config;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.annotation.PostRequest;
import com.example.demo.service.CommonRequestService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhougang <zhougang@kuaishou.com>
 * Created on 2020-09-01
 */
@Slf4j
public class MyHttpRequestServiceProxy implements InvocationHandler {

    private static final String NAMESPACE = "";

    private static final String TAG = "";

    private Class target;
    private CommonRequestService commonRequestService;
    private ConcurrentMap<Class, Object> instanceMap = new ConcurrentHashMap<>();

    public MyHttpRequestServiceProxy(Class target,
            CommonRequestService commonRequestService) {
        this.target = target;
        this.commonRequestService = commonRequestService;
    }

    public Object getProxy() {
        // 创建代理的核心逻辑
        return instanceMap.computeIfAbsent(target,
                key -> Proxy.newProxyInstance(target.getClassLoader(), new Class[] {target}, this));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getDeclaringClass().getName() + "." + method.getName());
        if (method.isAnnotationPresent(PostRequest.class)) {
            String url = method.getAnnotation(PostRequest.class).url();
            Object requestBody = args[0];
            Object result = handle(url, requestBody, method.getReturnType());
            return result;
        }
        return null;
    }

    private Object handle(String url, Object requestBody, Class<?> returnType) {
        String result = commonRequestService.post(url, requestBody);
        if (String.class.equals(returnType)) {
            return result;
        }
        return JSONObject.parseObject(result, returnType);
    }

}
