package com.wanwei.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName : ChatApplication
 * @Description : 聊天服务
 * @Author : LuoHongyu
 * @Date : 2021-10-23 16:10
 */
@SpringBootApplication(scanBasePackages = "com.wanwei")
@EnableFeignClients(basePackages = "com.wanwei")
@EnableDiscoveryClient
public class ChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatApplication.class);
    }
}