package org.jooye.thinking.in.spring.bean.definition;

import org.jooye.thinking.in.spring.bean.factory.DefaultUserFactory;
import org.jooye.thinking.in.spring.bean.factory.UserFactory;
import org.jooye.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @author :Jone
 * @date : 2023/5/7 18:02
 * @Describe: Bean 初始化
 */
@Configuration
public class BeanInitialization {
    public static void main(String[] args) {
        //创建BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册Configuration Class（配置类）
        applicationContext.register(BeanInitialization.class);

        //启动Spring应用上下文
        applicationContext.refresh();
        System.out.println("Spring应用上下文已启动....");
        //依赖查找
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);

        System.out.println(userFactory);
        //关闭Spring应用上下文
        applicationContext.close();
        System.out.println("Spring 应用上下文已关闭...");
    }

    @Bean(initMethod = "initUserFactory",destroyMethod = "doDestroy")
    @Lazy(value = false)
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }
}
