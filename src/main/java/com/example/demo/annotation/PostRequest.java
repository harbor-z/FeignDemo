package com.example.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author zhougang <zhougang@kuaishou.com>
 * Created on 2020-08-29
 */
@Retention(RetentionPolicy.RUNTIME)//描述注解在运行期间有效
@Target(ElementType.METHOD)//描述注解修饰哪类成员
public @interface PostRequest {
    String url() default "https://www.kuaishou.com/";
}
