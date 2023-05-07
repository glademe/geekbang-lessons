package org.jooye.thinking.in.spring.bean.factory;

import org.jooye.thinking.in.spring.ioc.overview.domain.User;

/**
 * {@link User}工厂类
 *
 * @author :Jone
 * @date : 2023/5/7 16:57
 * @Describe: 类的描述信息
 */
public interface UserFactory {

    default User createUser() {
        return User.createUser();
    }

    void initUserFactory();

    void doDestroy();

}
