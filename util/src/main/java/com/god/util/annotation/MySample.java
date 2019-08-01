package com.god.util.annotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/7/31 14:20
 * @ClassName: MySample
 * @Description: 自定义注解应用和测试
 */
@MyClassAnnotation(uri = "com.god.util.annotation.MySample", desc = "This is name")
public class MySample {

    @MyFieldAnnotation(uri = "com.god.util.annotation.MySample#id", desc = "This is Mysample#id")
    public String id;

    @MyFieldAnnotation(uri = "com.god.util.annotation.MySample#name", desc = "This is MySample#name")
    private String name;

    @MyConstructorAnnotation(uri = "com.god.util.annotation.MySample#MySample", desc = "This is default constuctor")
    public MySample() {}

    @MyMethodAnnotation(uri = "com.god.util.annotation.MySample#Method", desc = "This is Method")
    public void setId(String id) {
        this.id = id;
    }

    @MyMethodAnnotation(uri = "com.god.util.annotation.MySample#Method", desc = "This is Method")
    private void setName(String name) {
        this.name = name;
    }

    /**
     * 通过获取类和方法，字段上的注解
     * @param args
     * @throws NoSuchMethodException
     * @throws NoSuchFieldException
     */
    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException {


        MySample mySample = new MySample();

        MyClassAnnotation myClassAnnotation = MySample.class.getAnnotation(MyClassAnnotation.class);

        if (MySample.class.isAnnotation()) {
            System.out.println("是否存在注解");
        }

        System.out.println("Class's uri:" + myClassAnnotation.uri() + ";desc:" + myClassAnnotation.desc());

        Constructor constructor = mySample.getClass().getConstructor();

        MyConstructorAnnotation myConstructorAnnotation = (MyConstructorAnnotation) constructor.getAnnotation(MyConstructorAnnotation.class);

        System.out.println("Constructor's uri: " + myConstructorAnnotation.uri() + "; desc: " + myConstructorAnnotation.desc());

        Method method = mySample.getClass().getMethod("setId", String.class);

        if (method.isAnnotationPresent(MyMethodAnnotation.class)) {
            System.out.println("是否存在注解");
        }

        MyMethodAnnotation myMethodAnnotation = method.getAnnotation(MyMethodAnnotation.class);

        System.out.println("Method's uri: "  + myMethodAnnotation.uri() + "; desc: "  + myMethodAnnotation.desc());

        Method method1 = mySample.getClass().getDeclaredMethod("setName", String.class);

        MyMethodAnnotation myMethodAnnotation1 = method1.getAnnotation(MyMethodAnnotation.class);

        System.out.println("Method's uri: "  + myMethodAnnotation1.uri() + "; desc: "  + myMethodAnnotation1.desc());

        Field field = mySample.getClass().getField("id");
        MyFieldAnnotation myFieldAnnotation = field.getAnnotation(MyFieldAnnotation.class);

        System.out.println("Field's uri: "  + myFieldAnnotation.uri() + "; desc: "  + myFieldAnnotation.desc());

        Field name = mySample.getClass().getDeclaredField("name");
        MyFieldAnnotation myFieldAnnotation1 = name.getAnnotation(MyFieldAnnotation.class);

        System.out.println("Field's uri: "  + myFieldAnnotation1.uri() + "; desc: "  + myFieldAnnotation1.desc());
    }
}
