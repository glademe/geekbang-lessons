package org.jooye.thinking.in.spring.dependency.lookup;

import org.jooye.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.Iterator;

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
        lookupIfAvailable(applicationContext);
        lookupByStreamOps(applicationContext);
        applicationContext.close();

    }

    private static void lookupByStreamOps(AnnotationConfigApplicationContext applicationContext) {
        org.springframework.beans.factory.ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
//        Iterable<String> stringIterator = objectProvider;
//        for (String str : stringIterator) {
//            System.out.println(str);
//        }
        objectProvider.stream().forEach(System.out::println);
    }

    private static void lookupIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        org.springframework.beans.factory.ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        User user = beanProvider.getIfAvailable(User::createUser);
        System.out.println("当前 User 对象" + user);
    }


    @Bean
    @Primary
    public String helloWorld() {
        return "hello,world";
    }

    @Bean
    private String message() {
        return "Message";
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        org.springframework.beans.factory.ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(beanProvider.getObject());
    }
}
