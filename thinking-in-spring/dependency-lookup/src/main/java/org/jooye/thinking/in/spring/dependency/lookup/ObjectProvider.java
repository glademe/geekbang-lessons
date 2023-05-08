package org.jooye.thinking.in.spring.dependency.lookup;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * {@link org.springframework.beans.factory.BeanFactory}
 *
 * @author :Jone
 * @date : 2023/5/7 22:18
 * @Describe:类的描述信息
 */
public class ObjectProvider {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ObjectProvider.class);
        applicationContext.refresh();
        lookupByObjectProvider(applicationContext);
        applicationContext.close();

    }


    @Bean
    public String helloWorld() {
        return "hello,world";
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        org.springframework.beans.factory.ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(beanProvider.getObject());
    }
}
