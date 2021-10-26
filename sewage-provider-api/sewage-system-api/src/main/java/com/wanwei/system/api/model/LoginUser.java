package com.wanwei.system.api.model;


import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * 用户信息
 *
 * @author admin
 */
@Data
public class LoginUser implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 用户名id
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipAddr;

    /**
     * 权限列表
     */
    private Set<String> permissions;

    /**
     * 角色列表
     */
    private Set<String> roles;

    /**
     * 用户信息
     */
    private SysUser sysUser;

}
