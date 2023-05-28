package org.jooye.thinking.in.spring.ioc.bean.scope;

import org.jooye.thinking.in.spring.ioc.overview.domain.User;
import org.jooye.thinking.in.spring.ioc.overview.enums.City;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * @author :Jorry
 * @date : 2023-05-27 11:22
 * @Describe: Bean作用域
 */
public class BeanScope {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public static User singleUser() {
        return createUser();
    }


    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static User protoTypeUser() {
        return createUser();
    }


    private static User createUser() {
        User user = new User();
        user.setId(System.nanoTime());
        user.setCity(City.BEIJING);
        return user;
    }


    @Autowired
    @Qualifier("singleUser")
    private User singleUser;


    @Autowired
    @Qualifier("singleUser")
    private User singleUser1;

    @Autowired
    @Qualifier("protoTypeUser")
    private User protoTypeUser;

    @Autowired
    @Qualifier("protoTypeUser")
    private User protoTypeUser1;


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanScope.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
                @Override
                public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                    System.out.printf("%s Bean名称:%s,在初始化后回调。。。", bean.getClass(), bean.getClass().getName());
                    return bean;
                }
            });
        });
        applicationContext.refresh();

        scopeBeansByLookup(applicationContext);
        scopeBeansByInjection(applicationContext);
        applicationContext.close();
    }

    /**
     * 依赖注入
     *
     * @param applicationContext
     */
    private static void scopeBeansByInjection(AnnotationConfigApplicationContext applicationContext) {
        BeanScope bean = applicationContext.getBean(BeanScope.class);
        System.out.println("bean.singleUser = " + bean.singleUser);
        System.out.println("bean.singleUser1 = " + bean.singleUser1);
        System.out.println("bean.protoTypeUser = " + bean.protoTypeUser);
        System.out.println("bean.protoTypeUser1 = " + bean.protoTypeUser1);
    }

    /**
     * 依赖查找
     *
     * @param applicationContext
     */
    private static void scopeBeansByLookup(AnnotationConfigApplicationContext applicationContext) {
        for (int i = 0; i < 3; i++) {
            User singleUser = applicationContext.getBean("singleUser", User.class);
            System.out.println("singleUser = " + singleUser);

            User protoTypeUser = applicationContext.getBean("protoTypeUser", User.class);
            System.out.println("protoTypeUser = " + protoTypeUser);
        }
    }
}
