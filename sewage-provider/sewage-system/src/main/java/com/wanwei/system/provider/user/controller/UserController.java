package com.wanwei.system.provider.user.controller;

import com.wanwei.common.base.constants.SecurityConstants;
import com.wanwei.common.base.response.ObjectRestResponse;
import com.wanwei.common.security.util.SecurityUtils;
import com.wanwei.system.api.model.LoginUser;
import com.wanwei.system.api.model.SysUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : UserController
 * @Description :
 * @Author : LuoHongyu
 * @Date : 2021-10-25 22:55
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/getUserInfo")
    public ObjectRestResponse<LoginUser> getUserInfo(String userName, @RequestHeader(SecurityConstants.FROM_SOURCE) String source){
        LoginUser loginUser = new LoginUser();
        SysUser sysUser = new SysUser();
        sysUser.setPassword(SecurityUtils.encryptPassword("dddd222222"));
        loginUser.setSysUser(sysUser);
        return ObjectRestResponse.ok(loginUser);
    }

}
