package org.jooye.thinking.in.spring.ioc.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author :Jone
 * @date : 2023/5/13 22:42
 * @Describe: 基于XML资源的依赖Setter方式注入示例
 */
public class XmlDependencySetterInjection {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory =  new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpth:/"
        reader.loadBeanDefinitions("");
    }
}
