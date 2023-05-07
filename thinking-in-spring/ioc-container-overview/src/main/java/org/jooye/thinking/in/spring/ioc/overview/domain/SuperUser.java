package org.jooye.thinking.in.spring.ioc.overview.domain;

import org.jooye.thinking.in.spring.ioc.overview.annotation.Super;

/**
 * @author :Jone
 * @date : 2023/5/7 10:40
 * @Describe: 超级用户
 */
@Super
public class SuperUser extends User {

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }
}
