package org.jooye.thinking.in.spring.ioc.dependency.injection;

import org.jooye.thinking.in.spring.ioc.dependency.injection.annotation.UserGroup;
import org.jooye.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * {@link org.springframework.beans.factory.annotation.Qualifier} 注解依赖注入
 *
 * @author :Jone
 * @date : 2023/5/22 22:53
 * @Describe: Qualifier
 */
@Component
public class QualifierAnnotationDependencyInjection {

    @Autowired
    private User user;

    @Autowired
    @Qualifier("superUser")//指定Bean名称或者Id
    private User namedUser;


    @Autowired
    private Collection<User> allUser;


    @Autowired
    @Qualifier
    private Collection<User> qualifiedUsers;

    @Autowired
    @UserGroup
    private Collection<User> groupedUsers;

    @Bean
    @Qualifier
    public User user1() {
        return createUser(7L);
    }


    @Bean
    @Qualifier
    public User user2() {
        return createUser(8L);
    }


    @Bean
    @UserGroup
    public User user3() {
        return createUser(9L);
    }


    private static User createUser(Long id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(QualifierAnnotationDependencyInjection.class);
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(location);
        applicationContext.refresh();
        QualifierAnnotationDependencyInjection bean = applicationContext.getBean(QualifierAnnotationDependencyInjection.class);
        System.out.println("bean.user = " + bean.user);
        System.out.println("bean.namedUser = " + bean.namedUser);
        System.out.println("bean.allUser = " + bean.allUser);
        System.out.println("bean.qualifiedUsers = " + bean.qualifiedUsers);
        System.out.println("bean.groupedUsers = " + bean.groupedUsers);
        applicationContext.close();
    }
}
