package com.example.demo.config;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.service.CommonRequestService;


/**
 * @author zhougang <zhougang@kuaishou.com>
 * Created on 2020-09-01
 */
@Component
public class MyHttpRequestFactoryBean<T> implements FactoryBean<T> {

    @Autowired
    private CommonRequestService commonRequestService;

    private Class<T> clazz;

    public MyHttpRequestFactoryBean() {
    }

    public MyHttpRequestFactoryBean(Class<T> clazz) {
        this.clazz = clazz;
    }


    @Override
    public T getObject() throws Exception {
        // 创建代理
        return (T) new MyHttpRequestServiceProxy(clazz,commonRequestService).getProxy();
    }

    @Override
    public Class<?> getObjectType() {
        return clazz;
    }

    public void setClass(Class<T> clazz){
        this.clazz = clazz;
    }
}