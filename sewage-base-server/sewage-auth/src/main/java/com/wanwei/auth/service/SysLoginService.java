package com.wanwei.auth.service;

import com.wanwei.common.base.constants.BaseConstant;
import com.wanwei.common.base.constants.SecurityConstants;
import com.wanwei.common.base.constants.UserConstants;
import com.wanwei.common.base.enums.UserStatus;
import com.wanwei.common.base.exception.ValidateException;
import com.wanwei.common.base.response.ObjectRestResponse;
import com.wanwei.common.base.util.StringUtils;
import com.wanwei.common.security.util.SecurityUtils;
import com.wanwei.system.api.model.LoginUser;
import com.wanwei.system.api.model.SysUser;
import com.wanwei.system.api.service.RemoteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * 登录校验方法
 *
 * @author admin
 */
@Service
public class SysLoginService {
//    @Autowired
//    private RemoteLogService remoteLogService;

    @Autowired
    private RemoteUserService remoteUserService;

    /**
     * 登录
     */
    public LoginUser login(String username, String password) {
        // 用户名或密码为空 错误
        if (StringUtils.isAnyBlank(username, password)) {
            recordLogininfor(username, BaseConstant.LOGIN_FAIL, "用户/密码必须填写");
            throw new ValidateException("用户/密码必须填写");
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            recordLogininfor(username, BaseConstant.LOGIN_FAIL, "用户密码不在指定范围");
            throw new ValidateException("用户密码不在指定范围");
        }
        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            recordLogininfor(username, BaseConstant.LOGIN_FAIL, "用户名不在指定范围");
            throw new ValidateException("用户名不在指定范围");
        }
        // 查询用户信息 todo 调用系统服务查询用户数据
        ObjectRestResponse<LoginUser> result = remoteUserService.getUserInfo(username, SecurityConstants.INNER);

        if (HttpStatus.INTERNAL_SERVER_ERROR.value() == result.getStatus()) {
            throw new ValidateException(result.getMessage());
        }

        if (StringUtils.isNull(result) || StringUtils.isNull(result.getData())) {
            recordLogininfor(username, BaseConstant.LOGIN_FAIL, "登录用户不存在");
            throw new ValidateException("登录用户：" + username + " 不存在");
        }
        // 登录数据
        LoginUser user = result.getData();
        // 登录的用户数据
        SysUser sysUser = user.getSysUser();
        if (UserStatus.DELETED.getCode().equals(sysUser.getDelFlag())) {
            recordLogininfor(username, BaseConstant.LOGIN_FAIL, "对不起，您的账号已被删除");
            throw new ValidateException("对不起，您的账号：" + username + " 已被删除");
        }
        if (UserStatus.DISABLE.getCode().equals(sysUser.getStatus())) {
            recordLogininfor(username, BaseConstant.LOGIN_FAIL, "用户已停用，请联系管理员");
            throw new ValidateException("对不起，您的账号：" + username + " 已停用");
        }
        if (!SecurityUtils.matchesPassword(password, sysUser.getPassword())) {
            recordLogininfor(username, BaseConstant.LOGIN_FAIL, "用户密码错误");
            throw new ValidateException("用户不存在/密码错误");
        }
        recordLogininfor(username, BaseConstant.LOGIN_SUCCESS, "登录成功");
        return user;
    }

    public void logout(String loginName) {
        recordLogininfor(loginName, BaseConstant.LOGOUT, "退出成功");
    }

    /**
     * 注册
     */
    public void register(String username, String password) {
        // 用户名或密码为空 错误
        if (StringUtils.isAnyBlank(username, password)) {
            throw new ValidateException("用户/密码必须填写");
        }
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            throw new ValidateException("账户长度必须在2到20个字符之间");
        }
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            throw new ValidateException("密码长度必须在5到20个字符之间");
        }

        // 调用系统服务进行注册
        ObjectRestResponse registerResult = remoteUserService.registerUserInfo(null, "系统1");
        if (registerResult.getStatus()!=HttpStatus.OK.value()) {
            throw new ValidateException(registerResult.getMessage());
        }
        recordLogininfor(username, BaseConstant.REGISTER, "注册成功");
    }

    /**
     * 记录登录信息 调用日志服务
     *
     * @param username 用户名
     * @param status   状态
     * @param message  消息内容
     * @return
     */
    public void recordLogininfor(String username, String status, String message) {
        // todo 调用日志服务进行日志存储
//        SysLogininfor logininfor = new SysLogininfor();
//        logininfor.setUserName(username);
//        logininfor.setIpaddr(IpUtils.getIpAddr(ServletUtils.getRequest()));
//        logininfor.setMsg(message);
//        // 日志状态
//        if (StringUtils.equalsAny(status, BaseConstant.LOGIN_SUCCESS, BaseConstant.LOGOUT, BaseConstant.REGISTER)) {
//            logininfor.setStatus("0");
//        } else if (BaseConstant.LOGIN_FAIL.equals(status)) {
//            logininfor.setStatus("1");
//        }
//        remoteLogService.saveLogininfor(logininfor, SecurityBaseConstant.INNER);
    }
}