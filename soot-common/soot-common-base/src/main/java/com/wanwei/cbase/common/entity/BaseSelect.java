package com.wanwei.cbase.common.entity;

import lombok.Data;

/**
 * @ClassName : BaseSelect
 * @Description : 基础下拉框数据返回实体
 * @Author : LuoHongyu
 * @Date : 2021-08-08 17:41
 */
@Data
public class BaseSelect {

    /**
     * id : id
     * name : 下拉框名称
     * nameOne : 备用
     */
    private String id;
    private String name;
    private String nameOne;
}
