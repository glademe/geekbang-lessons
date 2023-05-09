package org.jooye.thinking.in.spring.dependency.lookup;

import org.jooye.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author :Jone
 * @date : 2023/5/9 20:07
 * @Describe:类的描述信息
 */
public class TypeSafetyDependencyLookup {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(TypeSafetyDependencyLookup.class);

        applicationContext.refresh();
        //验证BeanFactory#Bean
        displayBeanFactoryGetBean(applicationContext);
        displayBeanFactoryGetObject(applicationContext);
        displayObjectProviderIfAvailable(applicationContext);
        displayListableBeanFactoryOfBeansType(applicationContext);
        applicationContext.close();
    }

    private static void displayListableBeanFactoryOfBeansType(ListableBeanFactory applicationContext) {
        printBeansException("displayListableBeanFactory", () -> applicationContext.getBeansOfType(User.class));
    }

    private static void displayObjectProviderIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderIfAvailable", () -> beanProvider.getIfAvailable());
    }

    private static void displayBeanFactoryGetObject(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayBeanFactoryGetObject", () -> beanProvider.getObject());
    }


    public static void displayBeanFactoryGetBean(BeanFactory beanFactory) {
        printBeansException("displayBeanFactoryGetObject", () -> beanFactory.getBean(User.class));

    }

    private static void printBeansException(String source, Runnable runnable) {
        System.out.println("===================================");
        System.out.println("source from :" + source);
        try {
            runnable.run();
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }
}
