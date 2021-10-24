package com.wanwei.common.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * BussAnnotation
 * @author qbk
 * @version 1.0 2018/6/13
 * @since 1.0
 */
@Retention(RetentionPolicy.RUNTIME)  
@Target({ElementType.METHOD})  
public @interface BussAnnotation {
    //操作内容  
    String value() default "";
}  