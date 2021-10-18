package com.wanwei.system.provider.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wanwei.common.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * @ClassName : DictCopy1
 * @Description :
 * @Author : admin
 * @Date: 2021-10-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_dict_copy1")
public class DictCopy1 extends BaseEntity implements java.io.Serializable {

    /**
      * 
      */
    @TableId(type = IdType.ASSIGN_ID)
    private Integer id;
    /**
      * 字典值
      */
    @TableField(value = "dict_code")
    private String dictCode;
    /**
      * 字典名
      */
    @TableField(value = "dict_name")
    private String dictName;
    /**
      * 类型
      */
    @TableField(value = "type_code")
    private String typeCode;
    /**
      * 0启用 1关闭
      */
    @TableField(value = "enable_state")
    private Integer enableState;
    /**
      * 
      */
    @TableField(value = "sorting")
    private Integer sorting;

}