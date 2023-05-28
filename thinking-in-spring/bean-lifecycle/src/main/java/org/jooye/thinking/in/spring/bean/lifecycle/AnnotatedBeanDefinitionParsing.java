package org.jooye.thinking.in.spring.bean.lifecycle;

import org.jooye.thinking.in.spring.ioc.overview.domain.SuperUser;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.util.ObjectUtils;

/**
 * @author :Jorry
 * @date : 2023-05-27 19:28
 * @Describe:
 */
public class AnnotatedBeanDefinitionParsing {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //基于Java注解的AnnotatedBeanDefinitionReader
        AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(beanFactory);

        int beanCountBefore = beanFactory.getBeanDefinitionCount();
        //注册当前类（非Component）
        reader.register(AnnotatedBeanDefinitionParsing.class);
        int beanCountAfter = beanFactory.getBeanDefinitionCount();

        int count = beanCountAfter - beanCountBefore;

        System.out.printf("加载的Bean数量为：%s%n", count);


        AnnotatedBeanDefinitionParsing annotatedBeanDefinitionParsing = beanFactory.getBean("annotatedBeanDefinitionParsing", AnnotatedBeanDefinitionParsing.class);
        System.out.println("annotatedBeanDefinitionParsing = " + annotatedBeanDefinitionParsing);
    }

}
