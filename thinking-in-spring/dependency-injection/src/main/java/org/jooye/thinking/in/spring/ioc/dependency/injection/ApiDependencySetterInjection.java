package org.jooye.thinking.in.spring.ioc.dependency.injection;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author :Jone
 * @date : 2023/5/19 23:14
 * @Describe: 基于API的依赖Setter方法注入示例
 */
public class ApiDependencySetterInjection {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //生成UserHolder的BeanDefinition
        BeanDefinition beanDefinition = createUserHolderBeanDefinition();
        //注册UserHolder的BeanDefinition
        applicationContext.registerBeanDefinition("userHolder", beanDefinition);
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        //加载XMl资源，解析并生成BeanDefinition
        reader.loadBeanDefinitions(location);
        applicationContext.refresh();
        UserHolder userHolder = applicationContext.getBean(UserHolder.class);
        System.out.println("userHolder = " + userHolder);
        applicationContext.close();
    }


    /**
     * {@link UserHolder} 生成{@link BeanDefinition}
     *
     * @return
     */
    private static BeanDefinition createUserHolderBeanDefinition() {
        BeanDefinitionBuilder beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
        //添加Bean的ref
        beanDefinition.addPropertyReference("user", "superUser");
        return beanDefinition.getBeanDefinition();
    }
}
