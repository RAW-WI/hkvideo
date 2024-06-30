package com.softeem.springbootdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//配置映射器所在的包地址（Spring容器会自动扫描并注册，包地址通常为数据访问层，即dao/mapper）
@MapperScan("com.softeem.springbootdemo.dao")
@SpringBootApplication
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class,args);
    }
}
