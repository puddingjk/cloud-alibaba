package com.wanwei.common.base.response;


import com.wanwei.common.base.system.api.system.cbase.common.constants.Constants;

/**
 * @ClassName : ObjectRestResponse
 * @Description : 通用返回实体
 * @Author : LuoHongyu
 * @Date : 2020-08-24 14:01
 */
public class ObjectRestResponse<T> extends BaseResponse {

    T data;
    boolean rel = true;

    public ObjectRestResponse() {
    }

    /**
     * 返回成功：带数据
     *
     * @param o
     * @return
     */
    public static <E> ObjectRestResponse<E> ok(E o) {
        return new ObjectRestResponse<>(Constants.ErrorCode.SUCCESS, "操作成功", o, true);
    }

    /**
     * 返回成功：带数据
     *
     * @return
     */
    public static <E> ObjectRestResponse<E> ok(String msg, E o) {
        return new ObjectRestResponse<>(Constants.ErrorCode.SUCCESS, msg, o, true);
    }

    /**
     * 返回500
     *
     * @param <E>
     * @return
     */
    public static <E> ObjectRestResponse<E> error() {
        return new ObjectRestResponse<>(Constants.ErrorCode.ERROR, "操作失败", null, false);
    }

    /**
     * 返回500错误消息
     *
     * @param <E>
     * @return
     */
    public static <E> ObjectRestResponse<E> error(String msg) {
        return new ObjectRestResponse<>(Constants.ErrorCode.ERROR, msg, null, false);
    }

    /**
     * 警告消息
     *
     * @param <E>
     * @return
     */
    public static <E> ObjectRestResponse<E> warn(String msg) {
        return new ObjectRestResponse<>(Constants.ErrorCode.WARN, msg, null, true);
    }

    /**
     * 警告消息
     *
     * @param <E>
     * @return
     */
    public static <E> ObjectRestResponse<E> warn(String msg,int status) {
        return new ObjectRestResponse<>(status, msg, null, true);
    }
    /**
     * 其它相关返回值
     *
     * @param <E>
     * @return
     */
    public static <E> ObjectRestResponse<E> other(int status, String message, E data, boolean rel) {
        return new ObjectRestResponse<>(status, message, data, rel);
    }

    public ObjectRestResponse(int status, String message, T data, boolean rel) {
        super(status, message);
        this.data = data;
        this.rel = rel;
    }

    public boolean isRel() {
        return rel;
    }

    public void setRel(boolean rel) {
        this.rel = rel;
    }


    public ObjectRestResponse rel(boolean rel) {
        this.setRel(rel);
        return this;
    }


    public ObjectRestResponse data(T data) {
        this.setData(data);
        return this;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}