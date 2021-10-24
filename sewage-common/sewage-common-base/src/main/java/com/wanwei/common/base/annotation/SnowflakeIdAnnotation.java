package com.wanwei.common.base.annotation;

import java.lang.annotation.*;

/**
 * @Author zhangjing
 * @Description //雪花算法id 注解，id设置该注解，添加时无数据则会自动设置值 
 * @Date 10:05 2021/4/2
 * @Param 
 * @return 
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
public @interface SnowflakeIdAnnotation {
}
