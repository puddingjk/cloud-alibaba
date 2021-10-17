package com.wanwei.common.base.system.api.system.cbase.common.entity;

import lombok.Data;

/**
 * @ClassName : SysUser
 * @Description : 系统用户数据
 * @Author : LuoHongyu
 * @Date : 2021-08-13 15:28
 */
@Data
public class SysUser {
    /**
     * id 用户ID
     * loginName 用户名
     * productGroupId 产品分组ID
     * productGroupName 产品分组名称
     * superAdmin 超级管理员：true 是 false不是
     * groupAdmin 产品线管理员：true 是 false不是
     */
    private String id;
    private String loginName;
    private String userName;
    private String productGroupId;
    private String productGroupName;
    private Boolean superAdmin;
    private Boolean groupAdmin;
}
