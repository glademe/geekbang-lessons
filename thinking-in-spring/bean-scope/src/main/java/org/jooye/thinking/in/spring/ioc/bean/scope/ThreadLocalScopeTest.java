package org.jooye.thinking.in.spring.ioc.bean.scope;

import org.jooye.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * @author :Jorry
 * @date : 2023-05-27 17:24
 * @Describe: 自定义Scope
 */
public class ThreadLocalScopeTest {


    @Bean
    @Scope(ThreadLocalScope.SCOPE_NAME)
    private User user() {
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ThreadLocalScopeTest.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            //注册自定义Scope
            beanFactory.registerScope(ThreadLocalScope.SCOPE_NAME, new ThreadLocalScope());
        });
        applicationContext.refresh();
        scopeBeansByLookup(applicationContext);
        applicationContext.close();
    }


    /**
     * 依赖查找
     *
     * @param applicationContext
     */
    private static void scopeBeansByLookup(AnnotationConfigApplicationContext applicationContext) {
        for (int i = 0; i < 3; i++) {
            Runnable runnable = () -> {
                User user = applicationContext.getBean("user", User.class);
                System.out.printf("[Thread id :%d] user %s%n= ", Thread.currentThread().getId(), user);
            };

            Thread thread = new Thread(runnable);
            thread.start();

        }
    }
}
