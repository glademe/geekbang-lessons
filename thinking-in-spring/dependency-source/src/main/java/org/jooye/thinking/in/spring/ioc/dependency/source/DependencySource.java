package org.jooye.thinking.in.spring.ioc.dependency.source;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * @author :Jone
 * @date : 2023/5/25 21:22
 * @Describe: 依赖来源示例
 */
public class DependencySource {


    //注入再postProcessProperties 方法执行，早于Setter注入，也早于@PostContruct
    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;


    @PostConstruct
    public void init() {
        System.out.println("beanFactory == application\t" + (beanFactory == applicationContext));
        System.out.println("beanFactory == application.getAutowireCapableBeanFactory\t" + (beanFactory == applicationContext.getAutowireCapableBeanFactory()));
        System.out.println("beanFactory == application.getAutowireCapableBeanFactory\t" + (resourceLoader == applicationContext));
        System.out.println("beanFactory == application.getAutowireCapableBeanFactory\t" + (applicationEventPublisher == applicationContext));
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(DependencySource.class);
        applicationContext.refresh();

        DependencySource bean = applicationContext.getBean(DependencySource.class);

        applicationContext.close();
    }
}
