package org.jooye.thinking.in.spring.bean.lifecycle;

import org.jooye.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * @author :Jorry
 * @date : 2023-05-27 19:03
 * @Describe: Bean元信息配置示例
 */
public class BeanMetadataConfiguration {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //实例化 Properties资源的BeanDefinitionReader
        PropertiesBeanDefinitionReader propertiesBeanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
        String location = "/META-INF/default.properties";
        //指定字符编码UTF-8
        Resource resource = new ClassPathResource(location);
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        int beanNumber = propertiesBeanDefinitionReader.loadBeanDefinitions(encodedResource);
        System.out.printf("已加载的BeanDefinition数量为%s", beanNumber);

        User user = beanFactory.getBean("user", User.class);

        System.out.println("user = " + user);

    }
}
