package com.wanwei.cbase.common.exception;


/**
 * TokenException
 *
 * @author qbk
 * @date 2019/11/7
 * @Param
 * @return
 * @see
 */
public class TokenException extends ValidateException {

    public TokenException() {
        this("TOKEN不合法");
    }

    public TokenException(String message) {
        super(message);
    }

    /**
     * 自定义消息和状态码
     * @param message
     * @param status
     */
    public TokenException(String message, int status) {
        super(message, status);
    }
}
