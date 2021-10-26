package com.wanwei.common.redis.config;

import com.wanwei.common.redis.properties.ModuleProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName : ModuleConfig
 * @Description :
 * @Author : LuoHongyu
 * @Date : 2021-10-24 10:59
 */
@Configuration
@EnableConfigurationProperties(ModuleProperties.class)
public class ModuleAutoConfig {
}
