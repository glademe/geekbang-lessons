package org.jooye.thinking.in.spring.ioc.dependency.injection;

import org.jooye.thinking.in.spring.ioc.dependency.injection.annotation.InjectUser;
import org.jooye.thinking.in.spring.ioc.dependency.injection.annotation.MyAutowired;
import org.jooye.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.util.*;

import static java.util.Arrays.asList;
import static org.springframework.context.annotation.AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME;

/**
 * 注解驱动的依赖注入处理过程
 *
 * @author :Jone
 * @date : 2023/5/23 22:53
 * @Describe:
 */
@Component
public class AnnotationDependencyInjection {

    @Autowired
    @Lazy
    private User lazyUser;//实时注入+延迟
    @Autowired
    private User user;//实时注入
    // 必须（required=true）
    //实时注入（eager=true）
    //通过类型(User.class)
    //字段名称（“user”）
    //是否首要（primary）

    @Autowired
    private Map<String, User> users;

    @MyAutowired
    private Optional<User> optionalUser;

    @Inject
    private User injectUser;


    @InjectUser
    private User injectUser1;

//    @Bean(value = AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
//    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
//        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
//        Set<Class<? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<>(asList(Autowired.class,Inject.class,InjectUser.class));
//        //替换原有注解处理,使用新注解@InjectedUser
//        beanPostProcessor.setAutowiredAnnotationTypes(autowiredAnnotationTypes);
//        return beanPostProcessor;
//    }

    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE - 3)
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        beanPostProcessor.setAutowiredAnnotationType(InjectUser.class);
        return beanPostProcessor;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyInjection.class);
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(location);
        applicationContext.refresh();
        AnnotationDependencyInjection bean = applicationContext.getBean(AnnotationDependencyInjection.class);
        System.out.println("bean.user = " + bean.user);
        System.out.println("bean.injectUser = " + bean.injectUser);
        System.out.println("bean.optionalUser = " + bean.optionalUser);
        System.out.println("bean.injectUser1 = " + bean.injectUser1);
        applicationContext.close();
    }
}
