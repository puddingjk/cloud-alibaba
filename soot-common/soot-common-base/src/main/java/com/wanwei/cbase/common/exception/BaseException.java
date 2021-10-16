package com.wanwei.cbase.common.exception;


import com.wanwei.cbase.common.constants.ErrorCodeEnum;

/**
 * BaseException
 * @author qbk
 * @version 1.0 2018/6/13
 * @since 1.0
 */
public class BaseException extends RuntimeException {

    private int status = 200;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BaseException() {
    }

    public BaseException(String message,int status) {
        super(message);
        this.status = status;
    }
    public BaseException(ErrorCodeEnum codeEnum, Object... args) {
        super(String.format(codeEnum.msg(), args));
        this.status = codeEnum.code();
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
