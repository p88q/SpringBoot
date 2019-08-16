package com.god.example.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/8/2 17:27
 * @ClassName: testLambda
 * @Description:
 */
public class TestLambda {

    /**
     * 以下是lambda表达式的重要特征:
     *      可选类型声明：不需要声明参数类型，编译器可以统一识别参数值。
     *      可选的参数圆括号：一个参数无需定义圆括号，但多个参数需要定义圆括号。
     *      可选的大括号：如果主体包含了一个语句，就不需要使用大括号。
     *      可选的返回关键字：如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定明表达式返回了一个数值。
     */

    public static void main(String[] args) {

        GreetingService greetingService = message -> System.out.println("1234567");

        greetingService.sayMessage("Runoob");

        int num = 1;

        ConverTer<Integer, String> s = (param) -> System.out.println(String.valueOf(param + num));

        s.convert(2);

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        List<Double> numbers = Arrays.asList(12.1, 21.1, 213.1);

        /**
         * 对集合进行迭代forEach
         * map将一个对象转换成另一个对象
         *
         */
        numbers.stream().map(x -> x + x * 0.5).forEach(x -> System.out.println(x));

        System.out.println("####################################################################");

        List<Integer> integers = Arrays.asList(12, 23, 2221);

        Integer integer = integers.stream().map(x -> x - 1 + x * 8).reduce((sum, x) -> sum + x).get();

        System.out.println(integer);

        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");

        List<Integer> integers1 = Arrays.asList(32, 1, 323123, 45);

        List<Integer> collect = integers1.stream().filter(x -> x > 4).collect(Collectors.toList());

        collect.stream().forEach(x -> System.out.println(x));

        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");

        List<String> langfuages = Arrays.asList("java", "python", "scala", "shell", "r");

        System.out.println("Language starts with J: ");

        filterTest(langfuages, x -> x.startsWith("j"));

        System.out.println("\nLanguage ends with a: ");

        filterTest(langfuages,x -> x.endsWith("a"));

        System.out.println("\nAll languages: ");

        filterTest(langfuages,x -> true);

        System.out.println("\nNo languages: ");

        filterTest(langfuages,x -> false);

        System.out.println("\nLanguage length bigger three: ");

        filterTest(langfuages,x -> x.length() > 4);

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        Consumer consumer = System.out::println;

        Consumer consumer1 = n -> System.out.println(n + "-f2");

        consumer.andThen(consumer1).accept("test");

        consumer.andThen(consumer).andThen(consumer).andThen(consumer).accept("test1");

        System.out.println("Function 函数编程方法，他代表的含义式函数，而函数经常是有输入和输出的，因此它含有一个apply方法，包含一个输入与一个输出");
    }

    public interface ConverTer<T1, T2> {
        void convert(int i);
    }

    public static void filterTest(List<String> languages, Predicate<String> condition) {
        languages.stream().filter(x -> condition.test(x)).forEach(x -> System.out.println(x + ""));
    }
}
