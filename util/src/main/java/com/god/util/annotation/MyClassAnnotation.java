package com.god.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/6/20 11:49
 * @ClassName: MyClassAnnotation
 * @Description: 自定义类注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyClassAnnotation {

    String uri();

    String desc();
}
