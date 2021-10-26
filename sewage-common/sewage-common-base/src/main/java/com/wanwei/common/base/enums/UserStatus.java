package com.wanwei.common.base.enums;

/**
 * 用户状态
 *
 * @author admin
 */
public enum UserStatus {
    /**
     * 正常状态
     */
    OK("0", "正常"),
    /**
     * 用户停用
     */
    DISABLE("1", "停用"),
    /**
     * 用户删除
     */
    DELETED("2", "删除");
    private final String code;
    private final String info;

    UserStatus(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
