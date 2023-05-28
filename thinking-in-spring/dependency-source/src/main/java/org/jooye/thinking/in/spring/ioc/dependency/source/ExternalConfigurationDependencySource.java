package org.jooye.thinking.in.spring.ioc.dependency.source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

/**
 * @author :Jorry
 * @date : 2023-05-27 10:40
 * @Describe: 类的描述信息
 */
@Configuration
@PropertySource(value = "/META-INF/default.properties", encoding = "UTF-8")
public class ExternalConfigurationDependencySource {

    @Value("${user.id:-1}")
    private String id;

    @Value("${usr.name}")
    private String name;

    @Value("${user.resource:classpath://default.properties}")
    private Resource resource;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(ExternalConfigurationDependencySource.class);
        applicationContext.refresh();

        ExternalConfigurationDependencySource bean = applicationContext.getBean(ExternalConfigurationDependencySource.class);

        System.out.println("bean.id = " + bean.id);
        System.out.println("bean.name = " + bean.name);
        System.out.println("bean.resource = " + bean.resource);
        applicationContext.close();
    }
}
