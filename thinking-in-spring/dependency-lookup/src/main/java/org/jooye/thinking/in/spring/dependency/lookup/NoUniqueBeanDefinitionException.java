package org.jooye.thinking.in.spring.dependency.lookup;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author :Jone
 * @date : 2023/5/13 9:05
 * @Describe:类的描述信息
 */
public class NoUniqueBeanDefinitionException extends Throwable {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();

        annotationConfigApplicationContext.register(NoUniqueBeanDefinitionException.class);
        annotationConfigApplicationContext.refresh();


        annotationConfigApplicationContext.getBean(String.class);
        annotationConfigApplicationContext.close();
    }

    @Bean
    public String bean1() {
        return "bean1";
    }

    @Bean
    public String bean2() {
        return "bean2";
    }
}
