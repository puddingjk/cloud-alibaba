package com.wanwei.common.base.entity;

import lombok.Data;

/**
 * @ClassName : BasePage
 * @Description : 分页对象
 * @Author : LuoHongyu
 * @Date : 2020-08-25 10:52
 */
@Data
public class BasePage {
    /**
     * page : 页码
     * limit : 页大小
     * sortField : 排序字段
     * sortBy : 排序类型（asc/desc)
     */
    private Integer page = 1;
    private Integer limit = 15;
    private String sortField;
    private String sortBy;
}
