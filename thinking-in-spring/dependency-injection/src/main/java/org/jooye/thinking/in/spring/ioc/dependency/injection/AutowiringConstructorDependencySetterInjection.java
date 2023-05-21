package org.jooye.thinking.in.spring.ioc.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author :Jone
 * @date : 2023/5/19 23:26
 * @Describe: Constructor 依赖Autowire方法注入示例
 */
public class AutowiringConstructorDependencySetterInjection {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:/META-INF/autowiring-dependency-constructor-injection.xml";
        //加载XMl资源，解析并生成BeanDefinition
        reader.loadBeanDefinitions(location);
        UserHolder userHolder = beanFactory.getBean(UserHolder.class);
        System.out.println("userHolder = " + userHolder);
    }
}
