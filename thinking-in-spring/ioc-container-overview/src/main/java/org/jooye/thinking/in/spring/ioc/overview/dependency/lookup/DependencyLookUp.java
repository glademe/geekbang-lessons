package org.jooye.thinking.in.spring.ioc.overview.dependency.lookup;

import org.jooye.thinking.in.spring.ioc.overview.annotation.Super;
import org.jooye.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * @author :Jone
 * @date : 2023/5/7 10:15
 * @Describe: 依赖查找示例
 */
public class DependencyLookUp {
    public static void main(String[] args) {
        //配置XML配置文件
        //启动Spring应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");

        //按照类型查找
        lookupByType(beanFactory);
        //实时查找
        lookupInRealTime(beanFactory);
        //延迟查找
        lookupInLazy(beanFactory);
        //按照集合对象
        lookupCollectionByType(beanFactory);
        //通过注解进行查找
        lookupByAnnotationType(beanFactory);
    }

    /**
     * 根据注解进行查询
     *
     * @param beanFactory
     */
    private static void lookupByAnnotationType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> userMap = (Map) listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("依赖查找：" + userMap);
        }
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有User集合对象：" + users);

        }
    }


    /**
     * 根据类型进行查找
     *
     * @param beanFactory
     */
    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("类型进行查找：" + user);
    }

    /**
     * 实时查找
     *
     * @param beanFactory
     */
    private static void lookupInRealTime(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println("实时查找： " + user);
    }

    /**
     * 延迟查找
     *
     * @param beanFactory
     */
    private static void lookupInLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延迟查找： " + user);

    }
}
