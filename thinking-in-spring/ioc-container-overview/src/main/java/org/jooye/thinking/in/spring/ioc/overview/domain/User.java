package org.jooye.thinking.in.spring.ioc.overview.domain;

import org.jooye.thinking.in.spring.ioc.overview.enums.City;

/**
 * @author :Jone
 * @date : 2023/5/7 10:18
 * @Describe:类的描述信息
 */
public class User {

    private Long id;


    private String name;


    private City city;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public static User createUser() {
        User user = new User();
        user.setId(10L);
        user.setName("Herry");
        return user;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city=" + city +
                '}';
    }
}
