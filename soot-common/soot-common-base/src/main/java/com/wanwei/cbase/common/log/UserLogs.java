package com.wanwei.cbase.common.log;

import java.lang.annotation.*;

/**
 * @ClassName : userLog
 * @Description : 自定义注解，用于记录日志
 * @Author : yanJinliang
 * @Date : 2021/8/13 9:30
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserLogs {
    // 操作类型
    String eventType() default "";

    // 操作详情
    String eventDetails() default "";
}
