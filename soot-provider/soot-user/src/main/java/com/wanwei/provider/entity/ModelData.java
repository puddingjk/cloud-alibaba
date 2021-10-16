package com.wanwei.provider.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wanwei.cbase.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * @ClassName : ModelData
 * @Description :
 * @Author : admin
 * @Date: 2021-10-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_model_data")
public class ModelData extends BaseEntity implements java.io.Serializable {

    /**
      * 
      */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
      * 
      */
    @TableField(value = "data_name")
    private String dataName;
    /**
      * 
      */
    @TableField(value = "data_key")
    private String dataKey;
    /**
      * 
      */
    @TableField(value = "data_unit")
    private String dataUnit;
    /**
      * 数据类型  
      */
    @TableField(value = "data_type")
    private String dataType;
    /**
      * 
      */
    @TableField(value = "remark")
    private String remark;

}