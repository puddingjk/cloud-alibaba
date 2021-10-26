package com.wanwei.gateway.config.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName : XssProperties
 * @Description : XSS跨站脚本配置
 * @Author : LuoHongyu
 * @Date : 2021-10-24 16:16
 */
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "security.xss")
@Data
public class XssProperties {

    /**
     * Xss开关
     */
    private Boolean enabled;

    /**
     * 排除路径
     */
    private List<String> excludeUrls = new ArrayList<>();
}
