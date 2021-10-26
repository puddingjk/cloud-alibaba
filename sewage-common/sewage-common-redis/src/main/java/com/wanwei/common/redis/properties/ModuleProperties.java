package com.wanwei.common.redis.properties;

import com.wanwei.common.redis.constant.RedisKey;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName : RedisServerProperties
 * @Description : 所引入的redis的服务名称
 * @Author : LuoHongyu
 * @Date : 2021-10-24 10:43
 */
@ConfigurationProperties(prefix = "redis.module")
@Slf4j
@Data
public class ModuleProperties {
    /**
     * 设置当前redis的模块名称
     */
    private String name;
    /**
     * 权限中心名称 默认为auth
     */
    private String auth = RedisKey.SER_AUTH;
}
