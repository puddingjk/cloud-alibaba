package com.wanwei.gateway.config;

import com.wanwei.gateway.handler.SentinelFallbackHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @ClassName : GatewayConfig
 * @Description : 网关限流配置
 * @Author : LuoHongyu
 * @Date : 2021-10-24 16:09
 */
public class GatewayConfig {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelFallbackHandler sentinelGatewayExceptionHandler() {
        return new SentinelFallbackHandler();
    }
}
