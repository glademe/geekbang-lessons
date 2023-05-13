package org.jooye.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * {@link BeanCreationException}
 *
 * @author :Jone
 * @date : 2023/5/13 21:51
 * @Describe: 类的描述信息
 */
public class BeanCreationException {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        BeanDefinitionBuilder bd = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        applicationContext.registerBeanDefinition("errorBean", bd.getBeanDefinition());
        applicationContext.refresh();

        applicationContext.close();
    }


    public static class User implements InitializingBean {

        @Override
        public void afterPropertiesSet() throws Exception {
            throw new Exception("For purposes....");
        }
    }
}
