package com.god.example.lambda;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/8/5 10:35
 * @ClassName: TestLambda1
 * @Description:
 */
public class TestLambda1 {

    public static void main(String[] args) {
        Function<Integer, Integer> f = s -> s++;
        Function<Integer, Integer> g = s -> s * 2;

        System.out.println(f.compose(g).apply(1));

        System.out.println(f.andThen(g).apply(1));

        System.out.println(Function.identity().apply("a"));

        System.out.println("Predicate为函数式接口，predicate的中文意思式“断定”，即判断的意思，判断某个东西是否满足某种条件");
        // 创建空的stream
        Stream stream = Stream.empty();
        // 通过of创建stream
        Stream<String> test = Stream.of("test");
        // 通过Stream中的iterate方法创建

        Stream.generate(new Supplier<Double>() {
            @Override
            public Double get() {
                return Math.random();
            }
        }).limit(10).forEach(System.out::println);

        // 上述写法可以简化成一下写法：
        Stream.generate(() -> Math.random()).limit(20).forEach(System.out::println);

        Stream<String> s = Stream.of("", "test", "te");

        s.map(n -> n.concat(".exe")).forEach(System.out::println);

        Stream<String> ss = Stream.of("test", "t1", "t2", "teeee", "aaa");

        ss.flatMap(n -> Stream.of(n.split(""))).forEach(System.out::println);

        String ds = "te";

        Optional<String> o = Optional.ofNullable(ds);

        System.out.println(o.orElse(""));

    }


}
