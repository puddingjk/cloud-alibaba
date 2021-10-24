package com.wanwei.common.redis.starter.util;


import com.wanwei.common.base.constants.GlobalConstant;
import com.wanwei.common.base.dto.LoginUser;
import com.wanwei.common.base.util.ThreadLocalMap;
import com.wanwei.common.redis.starter.constant.RedisKey;

/**
 * AuthUtil
 * @author qbk
 * @version 1.0 2018/12/17
 * @since 1.0
 */
public class AuthUtil {

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
}
