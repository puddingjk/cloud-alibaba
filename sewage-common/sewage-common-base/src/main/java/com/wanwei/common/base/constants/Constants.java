package com.wanwei.common.base.constants;

/**
 * @ClassName : Constants
 * @Description : 常量配置
 * @Author : LuoHongyu
 * @Date : 2020-08-24 10:39
 */
public class Constants {

    public static class ErrorCode {
        // 成功
        public static final int SUCCESS = 200;
        // 失败
        public static final int ERROR = 500;
        // 警告
        public static final int WARN = 301;
    }
    /**
     * Token相关变量
     */
    public static class Token {
        public static final String ACCESS_TOKEN = "access_token";
        public static final String ACCESS_TOKEN_REDIS = "access_token:";
        public static final String REFRESH_TOKEN = "refresh_token";
        public static final String TIMESTAMP = "timestamp";
        // token错误
        public static final int ERROR_4001 = 4001;
        // 请求头错误
        public static final int HEADER_ERROR = 4003;
        // token刷新异常
        public static final int REFRESH_ERROR = 4004;
    }

    /**
     * 用户返回状态码
     */
    public static class User {
        // 用户名密码错误
        public final static int PARAM_ERROR = 6000;
        // 账号已冻结 错误5次后冻结
        public final static int USER_FREEZE = 6001;
        // 验证码错误
        public final static int CODE_ERROR = 6002;
        // 用户停用
        public final static int USER_STOP = 6005;
        // 用户不存在
        public final static int USER_UNKNOWN = 6006;
        // 账号已过期
        public final static int USER_EXPIRE = 6007;
        // 该用户无菜单权限
        public final static int USER_NOT_AUTH = 6008;
    }

    public static class RedisExpire {
        // redis 锁过期时间
        public static final long DEFAULT_EXPIRE = 60;
        public static final int SESSION_TIMEOUT = 2 * 60 * 60;//默认2h
    }

    /**
     * 分页相关
     */
    public static class Pageable {
        public static final String PAGE = "page";
        public static final String LIMIT = "limit";
    }


    /**
     * 字典相关
     */
    public static class Dict {
        // 物模型类型
        public static final String MODEL_TYPE = "MXLX";
        // 协议类型
        public static final String PROTOCOL_TYPE = "XYLX";
        // 事件类型
        public static final String EVENT_TYPE = "EVTYPE";
        // 动作类型
        public static final String ACTION_TYPE = "ACTYPE";
        // 云盒应答状态码
        public static final String SERVER_RESULT = "YDZTM";
        // 数据类型
        public static final String SJLX = "PZSJLX";
        // redis数据字典的key
        public static final String KEY_DICT = "dict";
    }

}
