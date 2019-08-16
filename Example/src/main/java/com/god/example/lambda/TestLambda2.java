package com.god.example.lambda;

import java.util.function.Consumer;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/8/5 15:04
 * @ClassName: TestLambda2
 * @Description:
 */
public class TestLambda2 {

    public static void main(String[] args) {
        Consumer f = System.out::println;
        Consumer f2 = n -> System.out.println(n + "-F2");

        //执行完F后再执行F2的Accept方法
//        f.andThen(f2).accept("test");

        //连续执行F的Accept方法
        f.andThen(f).andThen(f).andThen(f2).accept("test1");
    }

}
