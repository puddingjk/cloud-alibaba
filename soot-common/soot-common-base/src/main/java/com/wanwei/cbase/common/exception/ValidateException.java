package com.wanwei.cbase.common.exception;


import com.wanwei.cbase.common.constants.Constants;

/**
 * @ClassName : UserRoleService
 * @Description : 验证异常父类 默认返回消息：参数不合法 默认状态码：301
 * @Author : admin
 * @Date : 2021-08-11
 */
public class ValidateException extends BaseException {
    /**
     * 验证异常 默认返回警告异常码：301
     */
    public ValidateException() {
        this("参数不合法");
    }

    /**
     * 验证异常 默认返回警告异常码：301
     *
     * @param message
     */
    public ValidateException(String message) {
        super(message, Constants.ErrorCode.WARN);
    }

    /**
     * 验证异常 对状态码需求较高
     *
     * @param message
     * @param status
     */
    public ValidateException(String message, int status) {
        super(message, status);
    }
}
