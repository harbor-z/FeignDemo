package com.example.demo.config;

import java.util.Set;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;


import com.example.demo.annotation.MyHttpRequest;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhougang <zhougang@kuaishou.com>
 * Created on 2020-09-01
 */
@Slf4j
public class ClassPathCtripRequestScanner extends ClassPathBeanDefinitionScanner {
    public ClassPathCtripRequestScanner(BeanDefinitionRegistry registry) {
        super(registry);
        // 在构造器中就加上filter，使它天生就可以认识我们的自定义注解
        this.addIncludeFilter(new AnnotationTypeFilter(MyHttpRequest.class));
    }

    /**
     * 这个方法是使扫描器能够扫描注解的核心
     * @param beanDefinition
     * @return
     */
    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }

    @Override
    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);

        if (beanDefinitions.isEmpty()) {
            logger.warn("扫描到的 beanDefinitions 是空的，无法进行进一步操作！");
        } else {
            // 调用修改BeanDefinition的方法
            processBeanDefinitions(beanDefinitions);
        }

        return beanDefinitions;
    }

    /**
     * 需要在这里把interface的beanClass转为特定的beanClass
     * @param beanDefinitions
     */
    private void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitions) {
        for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitions) {
            BeanDefinition beanDefinition = beanDefinitionHolder.getBeanDefinition();

            // 这个会使Spring优先选择对应的有参构造
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(beanDefinition.getBeanClassName());
            // 把扫描到的interface改为FactoryBean，这样便能以FactoryBean的方式初始化
            beanDefinition.setBeanClassName(MyHttpRequestFactoryBean.class.getName());
        }
    }

}
