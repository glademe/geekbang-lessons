package org.jooye.thinking.in.spring.ioc.dependency.injection;

import org.jooye.thinking.in.spring.ioc.dependency.injection.annotation.UserGroup;
import org.jooye.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * {@link org.springframework.beans.factory.ObjectProvider} 实现延迟依赖注入
 *
 * @author :Jone
 * @date : 2023/5/23 22:53
 * @Describe: Qualifier
 */
@Component
public class LazyAnnotationDependencyInjection {

    @Autowired
    private User user;//实时注入

    @Autowired
    private ObjectProvider<User> userObjectProvider;//延迟注入

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(LazyAnnotationDependencyInjection.class);
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(location);
        applicationContext.refresh();
        LazyAnnotationDependencyInjection bean = applicationContext.getBean(LazyAnnotationDependencyInjection.class);
        System.out.println("bean.user = " + bean.user);
        System.out.println("bean.userObjectProvider.getObject() = " + bean.userObjectProvider.getObject());//继承ObjectFactory
        bean.userObjectProvider.forEach(System.out::println);


        applicationContext.close();
    }
}
