package com.wanwei.agg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AggApplication {

    public static void main(String[] args) {
        SpringApplication.run(AggApplication.class);
    }
}
