package org.jooye.thinking.in.spring.ioc.overview.domain;

/**
 * @author :Jone
 * @date : 2023/5/7 10:18
 * @Describe:类的描述信息
 */
public class User {

    private Long id;


    private String name;


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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
