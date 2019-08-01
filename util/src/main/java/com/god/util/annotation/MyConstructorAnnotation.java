package com.god.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/7/31 14:13
 * @ClassName: MyConstructorAnnotation
 * @Description: 自定义构造方法注解
 */
@Target(ElementType.CONSTRUCTOR)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyConstructorAnnotation {

    String uri();

    String desc();
}
