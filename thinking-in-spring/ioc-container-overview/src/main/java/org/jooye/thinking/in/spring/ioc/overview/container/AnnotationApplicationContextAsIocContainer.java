package org.jooye.thinking.in.spring.ioc.overview.container;

import org.jooye.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * 注解能力 {@link org.springframework.context.ApplicationContext} 作为IOC 容器示例
 *
 * @author :Jone
 * @date : 2023/5/7 11:51
 * @Describe:
 */
@Configuration
public class AnnotationApplicationContextAsIocContainer {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //将当前类作为配置类
        applicationContext.register(AnnotationApplicationContextAsIocContainer.class);
        //启动应用上下文
        applicationContext.refresh();
        lookupCollectionByType(applicationContext);
    }

    @Bean
    public User user() {
        User user = new User();
        user.setId(2L);
        user.setName("Kobe");
        return user;
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有User集合对象：" + users);

        }
    }

}
