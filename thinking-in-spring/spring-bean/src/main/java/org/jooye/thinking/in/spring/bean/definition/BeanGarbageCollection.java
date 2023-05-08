package org.jooye.thinking.in.spring.bean.definition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author :Jone
 * @date : 2023/5/7 21:29
 * @Describe: Bean垃圾回收
 */
public class BeanGarbageCollection {
    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitialization.class);
        applicationContext.refresh();
        applicationContext.close();

        System.out.println("Spring应用上下文已关闭...");

        //强制触发GC
        System.gc();
    }
}
