package org.jooye.thinking.in.spring.ioc.dependency.injection;

import org.jooye.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

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

    @Autowired
    private Optional<User> optionalUser;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyInjection.class);
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(location);
        applicationContext.refresh();
        AnnotationDependencyInjection bean = applicationContext.getBean(AnnotationDependencyInjection.class);
        System.out.println("bean.user = " + bean.user);

        applicationContext.close();
    }
}
