package com.wanwei.auth.controller;

import com.wanwei.auth.dto.LoginUserBody;
import com.wanwei.auth.dto.RegisterBody;
import com.wanwei.auth.service.SysLoginService;
import com.wanwei.common.base.response.ObjectRestResponse;
import com.wanwei.common.base.util.JwtUtils;
import com.wanwei.common.base.util.StringUtils;
import com.wanwei.common.security.auth.AuthUtil;
import com.wanwei.common.security.service.TokenService;
import com.wanwei.common.security.util.SecurityUtils;
import com.wanwei.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * token 控制
 *
 * @author admin
 */
@RestController
public class TokenController {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysLoginService sysLoginService;

    @PostMapping("login")
    public ObjectRestResponse<?> login(@RequestBody LoginUserBody user) {
        // 用户登录
        LoginUser userInfo = sysLoginService.login(user.getUsername(), user.getPassword());
        // 获取登录token
        Map<String, Object> token = tokenService.createToken(userInfo);
        return ObjectRestResponse.ok(token);
    }

    @DeleteMapping("logout")
    public ObjectRestResponse<?> logout(HttpServletRequest request) {
        String token = SecurityUtils.getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            String username = JwtUtils.getUserName(token);
            // 删除用户缓存记录
            AuthUtil.logoutByToken(token);
            // 记录用户退出日志
            sysLoginService.logout(username);
        }
        return ObjectRestResponse.ok();
    }

    @PostMapping("refresh")
    public ObjectRestResponse<?> refresh(HttpServletRequest request) {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser)) {
            // 刷新令牌有效期
            tokenService.refreshToken(loginUser);
            return ObjectRestResponse.ok();
        }
        return ObjectRestResponse.ok();
    }

    @PostMapping("register")
    public ObjectRestResponse<?> register(@RequestBody RegisterBody registerBody) {
        // 用户注册
        sysLoginService.register(registerBody.getUsername(), registerBody.getPassword());
        return ObjectRestResponse.ok();
    }
}
