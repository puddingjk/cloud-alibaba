package com.wanwei.common.redis.starter.config;

import com.wanwei.common.redis.starter.interceptor.TokenInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName : TokenInterceptorAutoConfiguration
 * @Description :
 * @Author : LuoHongyu
 * @Date : 2021-10-23 16:26
 */
public class TokenInterceptorAutoConfiguration implements WebMvcConfigurer {

    /**
     * 注册自定义拦截器
     * @param registry 注册器对象
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenInterceptor());
    }
}
