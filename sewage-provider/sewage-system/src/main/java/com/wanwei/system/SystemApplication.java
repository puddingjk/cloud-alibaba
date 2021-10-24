package com.wanwei.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.wanwei.system.provider.*.mapper")
@EnableAsync
@EnableTransactionManagement
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class);
    }
}
