package org.jooye.thinking.in.spring.bean.definition;

import org.jooye.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author :Jone
 * @date : 2023/5/7 15:46
 * @Describe:类的描述信息
 */
public class BeanAlias {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definitions-context.xml");
        //通过别名获取
        User user = (User) beanFactory.getBean("user");
        User jooye_user = (User) beanFactory.getBean("jooye-user");
        System.out.println("是否一致： " + (user == jooye_user));
    }
}
