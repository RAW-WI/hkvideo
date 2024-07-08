package com.softeem.hkvideo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.softeem.hkvideo.mapper")
@SpringBootApplication
public class HkvideoApplication {
    public static void main(String[] args) {
        SpringApplication.run(HkvideoApplication.class, args);
        String accessKeyId = System.getenv("OSS_ACCESS_KEY_ID");
        String accessKeySecret = System.getenv("OSS_ACCESS_KEY_SECRET");
     // 检查环境变量是否已设置
        if (accessKeyId == null || accessKeySecret == null) {
            System.err.println("请确保已设置环境变量 OSS_ACCESS_KEY_ID 和 OSS_ACCESS_KEY_SECRET");
        } else {
            System.out.println("环境变量已正确配置。");
        }
    }

}
