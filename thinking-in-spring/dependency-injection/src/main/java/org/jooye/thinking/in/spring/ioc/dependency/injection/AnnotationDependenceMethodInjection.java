package org.jooye.thinking.in.spring.ioc.dependency.injection;

import org.jooye.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * @author :Jone
 * @date : 2023/5/19 23:06
 * @Describe: 基于Java注解的依赖注入方式
 */
public class AnnotationDependenceMethodInjection {

    //    @Resource
//    @Autowired
//    private UserHolder userHolder;

    private UserHolder userHolder;
    private UserHolder userHolder2;

    @Autowired
    public void initUserHolder(UserHolder userHolder) {
        this.userHolder = userHolder;
    }

    @Resource
    public void initUserHolder2(UserHolder userHolder) {
        this.userHolder2 = userHolder;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AnnotationDependenceMethodInjection.class);

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        //加载XMl资源，解析并生成BeanDefinition
        reader.loadBeanDefinitions(location);
        context.refresh();

        //通过依赖查找并且创建Bean
        AnnotationDependenceMethodInjection bean = context.getBean(AnnotationDependenceMethodInjection.class);

        UserHolder userHolder = bean.userHolder;
        UserHolder userHolder2 = bean.userHolder2;
        System.out.println("userHolder = " + userHolder);
        System.out.println("userHolder2 = " + userHolder2);
        context.close();
    }


    @Bean
    private UserHolder userHolder(User user) {
        return new UserHolder(user);
    }
}
