package org.jooye.thinking.in.spring.ioc.overview.repository;

import org.jooye.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

/**
 * @author :Jone
 * @date : 2023/5/7 10:57
 * @Describe: 用户信息仓库
 */
public class UserRepository {

    private Collection<User> users;


    private BeanFactory beanFactory;


    private ObjectFactory<ApplicationContext> objectFactory;

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public ObjectFactory<ApplicationContext> getObjectFactory() {
        return objectFactory;
    }

    public void setObjectFactory(ObjectFactory<ApplicationContext> objectFactory) {
        this.objectFactory = objectFactory;
    }

    @Override
    public String toString() {
        return "UserRepository{" +
                "users=" + users +
                '}';
    }
}
