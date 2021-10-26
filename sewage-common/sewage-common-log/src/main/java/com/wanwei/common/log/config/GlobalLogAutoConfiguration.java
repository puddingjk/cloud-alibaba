package com.wanwei.common.log.config;

import com.wanwei.common.log.interceptor.GlobalLogInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName : GlobalLogAutoConfiguration
 * @Description :
 * @Author : LuoHongyu
 * @Date : 2021-10-23 16:26
 */
public class GlobalLogAutoConfiguration implements WebMvcConfigurer {

    /**
     * 注册自定义拦截器
     * @param registry 注册器对象
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new GlobalLogInterceptor());
    }
}
