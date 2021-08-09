package com.example.demo.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.example.demo.config.MyHttpRequestScannerRegistrar;


/**
 * @author zhougang <zhougang@kuaishou.com>
 * Created on 2020-09-01
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MyHttpRequestScannerRegistrar.class)
public @interface MyHttpRequestScanner {
    /**
     * value 为包扫描的路径
     * @return
     */
    String[] value() default {};
}
