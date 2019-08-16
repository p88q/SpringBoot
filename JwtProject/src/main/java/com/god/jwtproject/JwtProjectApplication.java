package com.god.jwtproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement // 开启事务，在service层添加@Transaction
@MapperScan(basePackages = {"com.god.**.dao"}) // 扫描mapper.xml
@SpringBootApplication
public class JwtProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtProjectApplication.class, args);
    }

}
