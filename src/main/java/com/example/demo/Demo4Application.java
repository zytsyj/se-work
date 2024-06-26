package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.yy.dao")
@ComponentScan(basePackages = {"com.yy.controller"})
@ComponentScan(basePackages = {"com.yy.service"})
@ComponentScan(basePackages = {"com.example.demo.common"})
public class Demo4Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo4Application.class, args);

    }

}
