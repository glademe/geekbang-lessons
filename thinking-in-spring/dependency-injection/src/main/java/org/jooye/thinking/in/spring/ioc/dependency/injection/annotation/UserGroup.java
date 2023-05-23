package org.jooye.thinking.in.spring.ioc.dependency.injection.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

/**
 * 用户组注解 扩展{@link org.springframework.beans.factory.annotation.Qualifier}
 *
 * @author :Jone
 * @date : 2023/5/22 23:14
 * @Describe: 类的描述信息
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Qualifier
public @interface UserGroup {
}
