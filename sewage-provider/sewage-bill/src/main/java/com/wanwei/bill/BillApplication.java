package com.wanwei.bill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName : BillApplication
 * @Description : 单据服务
 * @Author : LuoHongyu
 * @Date : 2021-10-23 16:10
 */
@SpringBootApplication(scanBasePackages = "com.wanwei")
@EnableFeignClients(basePackages = "com.wanwei")
@EnableDiscoveryClient
public class BillApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillApplication.class);
    }
}
