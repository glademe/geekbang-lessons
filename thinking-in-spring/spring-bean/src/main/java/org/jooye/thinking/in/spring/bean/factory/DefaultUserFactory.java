package org.jooye.thinking.in.spring.bean.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * {@link UserFactory}
 *
 * @author :Jone
 * @date : 2023/5/7 17:01
 * @Describe:类的描述信息
 */
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    //1基于@PostConstruct注解
    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct:UserFactory 初始化中....");
    }

    public void initUserFactory() {
        System.out.println("自定义初始化方法：UserFactory初始化中");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet: UserFactory初始化过程中");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("@PreDestroy: UserFactory销毁中...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean#destroy: UserFactory销毁中...");
    }

    public void doDestroy() {
        System.out.println("自定义销毁方法doDestroy():UserFactory销毁中...");
    }
}
