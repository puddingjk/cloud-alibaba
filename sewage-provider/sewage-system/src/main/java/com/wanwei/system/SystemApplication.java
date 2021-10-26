package com.wanwei.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 * @ClassName : MessageApplication
 * @Description : 消息服务
 * @Author : LuoHongyu
 * @Date : 2021-10-23 16:10
 */
@SpringBootApplication(scanBasePackages = "com.wanwei")
@EnableFeignClients(basePackages = "com.wanwei")
@EnableDiscoveryClient
@MapperScan("com.wanwei.system.provider.*.mapper")
@EnableAsync
@EnableTransactionManagement
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class);
    }
}
