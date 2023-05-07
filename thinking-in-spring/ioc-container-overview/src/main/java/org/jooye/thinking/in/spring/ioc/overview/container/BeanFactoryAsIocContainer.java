package org.jooye.thinking.in.spring.ioc.overview.container;

import org.jooye.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * {@link BeanFactory}作为IOC容器实例
 *
 * @author :Jone
 * @date : 2023/5/7 11:43
 * @Describe: IOC容器示例
 */
public class BeanFactoryAsIocContainer {
    public static void main(String[] args) {
        //创建BeanFactory容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        //XML配置文件路径
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        //加载配置
        int i = reader.loadBeanDefinitions(location);
        System.out.println("bean定义加载数量： " + i);

        lookupCollectionByType(beanFactory);
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有User集合对象：" + users);

        }
    }
}
