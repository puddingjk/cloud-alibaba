package com.wanwei.cbase.common.response;

/**
 * @ClassName : BaseResponse
 * @Description : 通用返回实体
 * @Author : LuoHongyu
 * @Date : 2020-08-24 14:01
 */
public class BaseResponse {

    private int status = 200;
    private String message;

    public BaseResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public BaseResponse(String message) {
        this.message = message;
    }

    public BaseResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
