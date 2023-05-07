package org.jooye.thinking.in.spring.bean.definition;

import org.jooye.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author :Jone
 * @date : 2023/5/7 16:23
 * @Describe: Bean实例化示例
 */
public class BeanInstantiation {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-Instantiation-context.xml");
        User user = beanFactory.getBean("user-by-static-method", User.class);
        User userInstance = beanFactory.getBean("user-by-instance-method", User.class);
        User userByFactoryBean = beanFactory.getBean("user-by-factory-bean", User.class);
        System.out.println("user = " + user);
        System.out.println("userInstance = " + userInstance);
        System.out.println("userByFactoryBean = " + userByFactoryBean);

        System.out.println(userInstance == user);
    }
}
