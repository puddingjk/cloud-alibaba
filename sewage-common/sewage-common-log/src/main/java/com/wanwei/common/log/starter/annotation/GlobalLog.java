package com.wanwei.common.log.starter.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName : GlobalLog
 * @Description : 全局日志注解
 * @Author : LuoHongyu
 * @Date : 2021-10-23 16:08
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GlobalLog {

    /**
     * 方法描述
     * @return
     */
    String desc() default "";
}
