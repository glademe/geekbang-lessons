package org.jooye.thinking.in.spring.bean.factory;

import org.jooye.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * {@link org.jooye.thinking.in.spring.ioc.overview.domain.User}
 *
 * @author :Jone
 * @date : 2023/5/7 17:23
 * @Describe:类的描述信息
 */
public class UserFactoryBean implements FactoryBean {

    @Override
    public Object getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
