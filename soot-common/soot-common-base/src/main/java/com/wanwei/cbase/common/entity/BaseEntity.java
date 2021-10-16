package com.wanwei.cbase.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName : BaseEntity
 * @Description : 通用表字段
 * @Author : LuoHongyu
 * @Date : 2020-08-24 13:36
 */
@Data
public class BaseEntity implements Serializable {
    /**
     * createId 创建人 当前登录用户
     * createTime 创建时间
     * updateTime 末次更新时间
     * updateUserId 末次操作人ID
     */
    public String createId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date updateTime;
    public String updateUserId;

}
