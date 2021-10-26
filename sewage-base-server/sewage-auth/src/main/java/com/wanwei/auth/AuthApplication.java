package com.wanwei.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
/**
 * @ClassName : AuthApplication
 * @Description : auth启动类
 * @Author : LuoHongyu
 * @Date : 2021-10-23 16:10
 */
@SpringBootApplication(scanBasePackages = "com.wanwei")
@EnableFeignClients(basePackages = "com.wanwei")
@EnableDiscoveryClient
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
