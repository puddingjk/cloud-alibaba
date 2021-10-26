package com.wanwei.tax;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/**
 * @ClassName : TaxApplication
 * @Description : 财务服务
 * @Author : LuoHongyu
 * @Date : 2021-10-23 16:10
 */
@SpringBootApplication(scanBasePackages = "com.wanwei")
@EnableDiscoveryClient
public class TaxApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaxApplication.class);
    }
}
