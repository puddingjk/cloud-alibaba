package com.wanwei.cbase.common.exception;

import com.wanwei.cbase.common.response.ObjectRestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * @Classname WebExceptionHandler
 * @Description 全局异常处理类
 * @Date 2021-07-31 17:29
 * @Author luohongyu
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class WebExceptionHandler {

    @ExceptionHandler
    public ObjectRestResponse tokenException(TokenException e) {
        log.error("TOKEN异常:{}", e.getMessage());
        return ObjectRestResponse.other(e.getStatus(),e.getMessage(),null,false);
    }

    /**
     * 验证异常
     * @param e
     * @return
     */
    @ExceptionHandler
    public ObjectRestResponse validateException(ValidateException e) {
        log.error("验证异常:{}", e.getMessage());
        return ObjectRestResponse.warn(e.getMessage(),e.getStatus());
    }

    /**
     * 参数不可读异常
     * @param e
     * @return
     */
    @ExceptionHandler
    public ObjectRestResponse notReadException(HttpMessageNotReadableException e) {
        log.error("参数请求异常", e);
        return ObjectRestResponse.warn("参数不合法");
    }

    /***
     * @Param e
     * @description 未知异常
     * @author puddingjk
     * @date 2020-09-07 9:01
     */
    @ExceptionHandler
    public ObjectRestResponse unknownException(Exception e) {
        log.error("发生了未知异常", e);
        // 字段超长 等数据库异常
//        if(e instanceof DataIntegrityViolationException){
//            return ObjectRestResponse.warn("参数不合法");
//        }
        if(e instanceof HttpRequestMethodNotSupportedException){
            return ObjectRestResponse.error("请求方式错误");
        }
        // 发送邮件通知技术人员.
        return ObjectRestResponse.error();
    }

   /***
    * 文件上传超出设置文件大小
    * @Author wangwei
    * @Date 2021/8/24 11:04
    * @param e
    * @return: com.wanwei.common.response.ObjectRestResponse
    */
    @ExceptionHandler
    public ObjectRestResponse fieMaxException(MaxUploadSizeExceededException e) {
        log.error("文件过大", e);
        return ObjectRestResponse.warn("文件过大");
    }

}
