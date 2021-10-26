package com.wanwei.system.api.service;

import com.wanwei.common.base.constants.SecurityConstants;
import com.wanwei.common.base.response.ObjectRestResponse;
import com.wanwei.system.api.fallback.RemoteUserServiceFallbackFactory;
import com.wanwei.system.api.model.LoginUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName : RemoteUserService
 * @Description :
 * @Author : LuoHongyu
 * @Date : 2021-10-24 20:19
 */
@FeignClient(
        value = "sewage-system",
        fallbackFactory = RemoteUserServiceFallbackFactory.class
)
public interface RemoteUserService {

    /***
     * 根据用户名获取当前登录用户数据
     * @author luoHongyu
     * @date 2021-10-24 20:33
     * @param userName 用户名
     * @param source 请求来源(某某系统等，用于多平台区分账号权限数据)
     * @return com.wanwei.common.base.response.ObjectRestResponse
     */
    @GetMapping("/user/getUserInfo")
    ObjectRestResponse<LoginUser> getUserInfo(@RequestParam("userName")String userName, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 注册
     * @param registerUser
     * @param source
     * @return
     */
    @PostMapping("/user/registerUserInfo")
    ObjectRestResponse registerUserInfo(@RequestBody Object registerUser, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
