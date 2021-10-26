package com.wanwei.common.security.interceptor;

import com.wanwei.common.base.constants.GlobalConstant;
import com.wanwei.common.base.util.ThreadLocalMap;
import com.wanwei.common.redis.constant.RedisKey;
import com.wanwei.common.redis.properties.ModuleProperties;
import com.wanwei.common.redis.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TokenInterceptor
 *
 * @author qbk
 * @version 1.0 2018/12/17
 * @since 1.0
 */
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    @Resource
    private RedisUtil redisUtil;
    @Resource
    private ModuleProperties moduleProperties;

    private static final String OPTIONS = "OPTIONS";
    private static final String AUTH_PATH1 = "/auth";
    private static final String AUTH_PATH2 = "/oauth";
    private static final String AUTH_PATH3 = "/error";
    private static final String AUTH_PATH4 = "/api";

    /**
     * After completion.
     *
     * @param request  the request
     * @param response the response
     * @param arg2     the arg 2
     * @param ex       the ex
     *
     * @throws Exception the exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception ex) throws Exception {
        if (ex != null) {
            log.error("TokenInterceptor",ex);
            this.handleException(response,ex);
        }
    }

    /**
     * Post handle.
     *
     * @param request  the request
     * @param response the response
     * @param arg2     the arg 2
     * @param mv       the mv
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView mv) {
    }

    /**
     * Pre handle boolean.
     *
     * @param request  the request
     * @param response the response
     * @param handler  the handler
     *
     * @return the boolean
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String feignPort = request.getHeader(GlobalConstant.Sys.FEIGN_REQ_HEADER);
        if(StringUtils.isNotBlank(feignPort)){
            ThreadLocalMap.put(GlobalConstant.Sys.CURRENT_REQUEST_PORT, Integer.valueOf(feignPort));
        }else {
            int serverPort =  request.getServerPort();
            ThreadLocalMap.put(GlobalConstant.Sys.CURRENT_REQUEST_PORT, serverPort);
        }
        String uri = request.getRequestURI();
        if (uri.contains(AUTH_PATH1) || uri.contains(AUTH_PATH2) || uri.contains(AUTH_PATH3) || uri.contains(AUTH_PATH4)) {
            return true;
        }
        if (OPTIONS.equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(StringUtils.isBlank(authorization) || !authorization.contains("Bearer")){
            return true;
        }
        String token = StringUtils.substringAfter(authorization, "Bearer ");
        Object object= redisUtil.get(moduleProperties.getAuth()+ RedisKey.ACCESS_TOKEN_KEY +token);
        if (object == null) {
            return true;
        }
        ThreadLocalMap.put(GlobalConstant.Sys.TOKEN_AUTH_DTO, object);
        return true;
    }

    private void handleException(HttpServletResponse res,Exception ex) throws IOException {
        res.resetBuffer();
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write("{\"code\":100009 ,\"message\" :\""+ex.getMessage()+"\"}");
        res.flushBuffer();
    }


}