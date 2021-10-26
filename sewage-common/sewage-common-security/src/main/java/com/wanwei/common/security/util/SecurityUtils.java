package com.wanwei.common.security.util;

import com.wanwei.common.base.constants.GlobalConstant;
import com.wanwei.common.base.constants.TokenConstants;
import com.wanwei.common.base.util.ServletUtils;
import com.wanwei.common.base.util.StringUtils;
import com.wanwei.common.base.util.ThreadLocalMap;
import com.wanwei.common.redis.constant.RedisKey;
import com.wanwei.system.api.model.LoginUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName : SecurityUtils
 * @Description : 权限工具类
 * @Author : LuoHongyu
 * @Date : 2021-10-25 21:02
 */
public class SecurityUtils {


    public static LoginUser getLoginUser() {
        return (LoginUser) ThreadLocalMap.get(RedisKey.SER_AUTH + GlobalConstant.Sys.TOKEN_AUTH_DTO);
    }

    public static String getLoginName(){
        LoginUser userDto =  (LoginUser) ThreadLocalMap.get(RedisKey.SER_AUTH +GlobalConstant.Sys.TOKEN_AUTH_DTO);
        if(userDto != null){
            return userDto.getUserName();
        }
        return "";
    }

    /**
     * 获取请求token
     */
    public static String getToken() {
        return getToken(ServletUtils.getRequest());
    }

    /**
     * 根据request获取请求token
     */
    public static String getToken(HttpServletRequest request) {
        // 从header获取token标识
        String token = request.getHeader(TokenConstants.AUTHENTICATION);
        return replaceTokenPrefix(token);
    }

    /**
     * 裁剪token前缀
     */
    public static String replaceTokenPrefix(String token) {
        // 如果前端设置了令牌前缀，则裁剪掉前缀
        if (StringUtils.isNotEmpty(token) && token.startsWith(TokenConstants.PREFIX)) {
            token = token.replaceFirst(TokenConstants.PREFIX, "");
        }
        return token;
    }


    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword     真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
