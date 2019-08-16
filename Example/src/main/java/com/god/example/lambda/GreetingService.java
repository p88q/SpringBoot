package com.god.example.lambda;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/8/2 17:10
 * @ClassName: GreetingService
 * @Description:
 *      jdk8新增注解，主要用于编译级错误检查，加上该注解，当你写的接口不符合函数式编程时，编译器会报错
 *      不加该注解也行，
 */
@FunctionalInterface
public interface GreetingService {

    /**
     * 函数型接口：
     *      首先是一个接口，接口中只能有一个抽象的方法
     *      也成为SAM接口，即Single Abstract Method interfaces
     * 可以有Object中的public方法如equals方法
     * 也可以有静态方法
     */

    /**
     *
     * @param message
     */
    void sayMessage(String message);
}
