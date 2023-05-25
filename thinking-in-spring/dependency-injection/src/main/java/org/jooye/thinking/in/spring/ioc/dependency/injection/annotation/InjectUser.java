package org.jooye.thinking.in.spring.ioc.dependency.injection.annotation;

import java.lang.annotation.*;

/**
 * @author :Jone
 * @date : 2023/5/24 19:23
 * @Describe: 自定义依赖注入注解
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectUser {
}
