package org.jooye.thinking.in.spring.bean.lifecycle;

import org.jooye.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author :Jorry
 * @date : 2023-05-27 20:00
 * @Describe: 类的描述信息
 */
public class MergeBeanDefinition {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        String location = "META-INF/dependency-lookup-context.xml";
        int i = reader.loadBeanDefinitions(location);
        User user = beanFactory.getBean("user", User.class);
        System.out.println("user = " + user);

        User superUser = beanFactory.getBean("superUser", User.class);

        System.out.println("superUser = " + superUser);
    }
}
