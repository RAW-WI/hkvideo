package com.softeem.hkvideo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.softeem.hkvideo.mapper")
@SpringBootApplication
public class HkvideoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HkvideoApplication.class, args);
    }

}
