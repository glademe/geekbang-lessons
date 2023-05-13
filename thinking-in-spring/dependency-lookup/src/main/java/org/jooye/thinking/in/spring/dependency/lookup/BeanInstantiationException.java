package org.jooye.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author :Jone
 * @date : 2023/5/13 21:43
 * @Describe:类的描述信息
 */
public class BeanInstantiationException {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册BD
        BeanDefinitionBuilder bd = BeanDefinitionBuilder.genericBeanDefinition(CharSequence.class);
        applicationContext.registerBeanDefinition("errorBean", bd.getBeanDefinition());
        applicationContext.refresh();

        applicationContext.close();
    }
}
