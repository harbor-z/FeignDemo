package com.example.demo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import com.example.demo.annotation.MyHttpRequestScanner;


/**
 * @author zhougang <zhougang@kuaishou.com>
 * Created on 2020-09-01
 */
public class MyHttpRequestScannerRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
            BeanDefinitionRegistry registry) {
        AnnotationAttributes annoAttrs = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(
                MyHttpRequestScanner.class.getName()));
        List<String> basePackages = new ArrayList();
        // 获取CtripRequest里面的路径
        for (String pkg : annoAttrs.getStringArray("value")) {
            if (StringUtils.hasText(pkg)) {
                basePackages.add(pkg);
            }
        }
        ClassPathCtripRequestScanner scanner = new ClassPathCtripRequestScanner(registry);
        for (String basePackage : basePackages) {
            // 针对每个路径进行扫描
            scanner.doScan(basePackage);
        }
    }
}
