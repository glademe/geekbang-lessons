package org.jooye.thinking.in.spring.bean.definition;

import org.jooye.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author :Jone
 * @date : 2023/5/7 15:53
 * @Describe: 注解BeanDefinition示例
 */
@Import(AnnotationBeanDefinition.Config.class)
public class AnnotationBeanDefinition {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册Configuration class（配置类）
        applicationContext.register(AnnotationBeanDefinition.class);

        //通过BeanDefinition注册Api
        registerBeanDefinition(applicationContext,"xiaohe",User.class);

        //通过非命名
        registerBeanDefinition(applicationContext,User.class);
        applicationContext.refresh();
        //1.通过@Bean
        //2.通过@Component方式
        //3.通过Import来进行导入

        Map<String, Config> configMap = applicationContext.getBeansOfType(Config.class);
        System.out.println("config类型的所有Bean: " + configMap);
        Map<String, User> userMap = applicationContext.getBeansOfType(User.class);
        System.out.println("User类型的所有Bean: " + userMap);

        //显示关闭Spring应用上下文
        applicationContext.close();
    }


    public static void registerBeanDefinition(BeanDefinitionRegistry registry, String beanName, Class<?> beanClass) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(beanClass);
        beanDefinitionBuilder.addPropertyValue("id", 5L);
        beanDefinitionBuilder.addPropertyValue("name", "Kobe");

        //判断BeanName参数存在时
        if (StringUtils.hasText(beanName)) {
            //注册BeanDefinition
            registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        } else {
            //非命名的方式
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
        }
    }

    public static void registerBeanDefinition(BeanDefinitionRegistry registry, Class<?> beanClass) {
        registerBeanDefinition(registry, null, beanClass);
    }


    @Component//定义当前类作为Spring Bean（组件）
    public static class Config {
        @Bean(name = {"user", "james_user"})
        public User user() {
            User user = new User();
            user.setId(3L);
            user.setName("james");
            return user;
        }

    }


}
