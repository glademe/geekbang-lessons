package org.jooye.thinking.in.spring.bean.definition;

import org.jooye.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * {@link org.springframework.beans.factory.support.BeanDefinitionBuilder}
 *
 * @author :Jone
 * @date : 2023/5/7 15:12
 * @Describe:类的描述信息
 */
public class BeanDefinitionCreation {
    public static void main(String[] args) {
        //1.通过BeanDefinitionBuilder构建
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        //通过属性设置
        beanDefinitionBuilder
                .addPropertyValue("name", "zhangsan")
                .addPropertyValue("id", 2);

        //获取BeanDefinition实例
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        //BeanDefinition并非Bean终态,可以自定义修改



        //2.通过AbstractBeanDefinition以及派生类
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        //设置Bean类型
        genericBeanDefinition.setBeanClass(User.class);
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.addPropertyValue("name","Curry");
        propertyValues.addPropertyValue("id",2L);
        genericBeanDefinition.setPropertyValues(propertyValues);

    }
}
