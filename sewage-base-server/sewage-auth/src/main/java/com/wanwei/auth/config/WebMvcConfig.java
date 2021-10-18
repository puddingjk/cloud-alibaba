package com.wanwei.auth.config;


//import javax.annotation.Resource;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//import com.wanwei.dcloud2.redis.interceptor.TokenInterceptor;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

//	@Resource
//	private TokenInterceptor viewInterceptor;
//
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/**")
//				.addResourceLocations("classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/");
//	}
//
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		super.addInterceptors(registry);
//		registry.addInterceptor(viewInterceptor)
//				.addPathPatterns("/**")
//				.excludePathPatterns("/swagger-resources/**", "*.js", "/**/*.js", "*.css", "/**/*.css", "*.html", "/**/*.html");
//	}
}
