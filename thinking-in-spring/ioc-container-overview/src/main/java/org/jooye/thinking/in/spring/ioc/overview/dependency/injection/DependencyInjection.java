package org.jooye.thinking.in.spring.ioc.overview.dependency.injection;

import org.jooye.thinking.in.spring.ioc.overview.domain.User;
import org.jooye.thinking.in.spring.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @author :Jone
 * @date : 2023/5/7 10:15
 * @Describe: 依赖注入示例
 */
public class DependencyInjection {
    public static void main(String[] args) {
        //配置XML配置文件
        //启动Spring应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-Injection-context.xml");
        //依赖来源一：自定义Bean
        UserRepository userRepository = (UserRepository) beanFactory.getBean("userRepository");
//        System.out.println("userRepository = " + userRepository);

        //依赖来源二：依赖注入（内建依赖）
        System.out.println(userRepository.getBeanFactory());


//        System.out.println(beanFactory.getBean(BeanFactory.class));
        ObjectFactory userObjectFactory = userRepository.getObjectFactory();
        System.out.println(userObjectFactory.getObject() == beanFactory);

        //依赖来源三:容器内建 Bean
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println("获取 environment 类型的Bean" + environment);

    }

    private static void whoISIocContainer(UserRepository userRepository, BeanFactory beanFactory) {

        //这个表达式为什么不会成立
        System.out.println(userRepository.getBeanFactory() == beanFactory);

        //ApplicationContext is BeanFactory
    }


}
