package org.jooye.thinking.in.spring.ioc.dependency.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * {@link ConfigurableListableBeanFactory#registerResolvableDependency(Class, Object)}
 *
 * @author :Jorry
 * @date : 2023-05-27 10:25
 * @Describe: 类的描述信息
 */
public class registerResolvableDependencySource {


    @Autowired
    private String value;


    @PostConstruct
    public void init() {
        System.out.println("value = " + value);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(registerResolvableDependencySource.class);
        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            ConfigurableListableBeanFactory configurableListableBeanFactory = ConfigurableListableBeanFactory.class.cast(beanFactory);
            //注册 ResolvableDependency
            configurableListableBeanFactory.registerResolvableDependency(String.class, "Hello,world");
        });
        applicationContext.refresh();
        applicationContext.close();
    }
}
