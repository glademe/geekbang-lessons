package org.jooye.thinking.in.spring.ioc.dependency.injection;

import org.jooye.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author :Jone
 * @date : 2023/5/19 23:06
 * @Describe: 类的描述信息
 */
public class AnnotationDependenceConstructorInjection {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();


        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        //加载XMl资源，解析并生成BeanDefinition
        reader.loadBeanDefinitions(location);
        context.refresh();
        UserHolder userHolder = context.getBean(UserHolder.class);
        System.out.println("userHolder = " + userHolder);
        context.close();
    }


    @Bean
    private UserHolder userHolder(User user) {
        return new UserHolder(user);
    }
}
