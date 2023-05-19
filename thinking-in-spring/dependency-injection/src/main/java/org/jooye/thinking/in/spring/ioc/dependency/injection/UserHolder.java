package org.jooye.thinking.in.spring.ioc.dependency.injection;

import org.jooye.thinking.in.spring.ioc.overview.domain.User;

/**
 * {@link User}
 *
 * @author :Jone
 * @date : 2023/5/19 22:42
 * @Describe: 类的描述信息
 */
public class UserHolder {
    private User user;

    public UserHolder() {
    }

    public UserHolder(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
