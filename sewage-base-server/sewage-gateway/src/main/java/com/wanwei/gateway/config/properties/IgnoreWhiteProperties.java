package com.wanwei.gateway.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName : IgnoreWhiteProperties
 * @Description : 放心的白名单
 * @Author : LuoHongyu
 * @Date : 2021-10-24 16:15
 */
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "security.ignore")
public class IgnoreWhiteProperties {
    /**
     * 放行白名单配置，网关不校验此处的白名单
     */
    private List<String> whites = new ArrayList<>();

    public List<String> getWhites() {
        return whites;
    }

    public void setWhites(List<String> whites) {
        this.whites = whites;
    }
}
