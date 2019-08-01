package com.god.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/7/31 14:16
 * @ClassName: MyMethodAnnotation
 * @Description: 自定义字段注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyFieldAnnotation {

    String uri();

    String desc();

}
