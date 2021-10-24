package com.wanwei.common.log.starter.interceptor;

import com.wanwei.common.log.starter.annotation.GlobalLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @ClassName : GlobalLogInterceptor
 * @Description : 全局日志拦截器，存储日志的执行时间等日志
 * @Author : LuoHongyu
 * @Date : 2021-10-23 16:10
 */
@Slf4j
public class GlobalLogInterceptor extends HandlerInterceptorAdapter {


    private static final ThreadLocal<Long> startTimeThreadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            HandlerMethod methodHandler = (HandlerMethod)handler;
            Method method = methodHandler.getMethod();
            // 获取是否有全局日志注解
            GlobalLog globalLog = method.getAnnotation(GlobalLog.class);
            if(globalLog!=null){
                long startTime = System.currentTimeMillis();
                startTimeThreadLocal.set(startTime);
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if(handler instanceof HandlerMethod){
            HandlerMethod methodHandler = (HandlerMethod)handler;
            Method method = methodHandler.getMethod();
            GlobalLog globalLog = method.getAnnotation(GlobalLog.class);
            if(globalLog != null){
                long endTime = System.currentTimeMillis();
                long startTime = startTimeThreadLocal.get();
                long opTime = endTime - startTime;
                String requestURI = request.getRequestURI();
                String methodDesc = globalLog.desc();
                log.info("URL:{},  描述:{},耗时:{}ms",requestURI,methodDesc,opTime);
            }
        }
        super.postHandle(request, response, handler, modelAndView);
    }
}
