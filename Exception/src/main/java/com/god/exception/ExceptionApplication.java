package com.god.exception;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy // 启动对@AspectJ的支持
@SpringBootApplication
@ComponentScan("com.god")
public class ExceptionApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExceptionApplication.class, args);
    }

}

