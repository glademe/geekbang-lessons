package org.jooye.thinking.in.spring.bean.lifecycle;

import org.jooye.thinking.in.spring.ioc.overview.domain.SuperUser;
import org.jooye.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.ObjectUtils;

/**
 * {@link InstantiationAwareBeanPostProcessor}
 *
 * @author :Jorry
 * @date : 2023-05-27 22:17
 * @Describe: Bean实例化生命周期
 */
public class BeanInstantiationLifecycle {
    public static void main(String[] args) {
        //创建BeanFactory容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        //XML配置文件路径
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        //加载配置
        int i = reader.loadBeanDefinitions(location);
        System.out.println("bean定义加载数量： " + i);

        User user = beanFactory.getBean("user", User.class);
        System.out.println("user = " + user);


        SuperUser superUser = beanFactory.getBean("superUser", SuperUser.class);
        System.out.println("superUser = " + superUser);


    }

    static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals("superUser", beanName) && SuperUser.class.equals(beanClass)) {
                //把配置好的SuperUser Bean 覆盖
                return new SuperUser();
            }
            return null;//保存Spring Ioc实例化操作
        }
    }
}
