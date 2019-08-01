package com.god.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/7/31 14:16
 * @ClassName: MyMethodAnnotation
 * @Description: 自定义方法注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyMethodAnnotation {

    String uri();

    String desc();
}
