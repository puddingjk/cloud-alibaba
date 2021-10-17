package com.wanwei.common.base.system.api.system.cbase.common.constants;

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

    /***
     * 原始报文数据
     * @Author wangwei
     * @Date 2021/8/13 13:36
     */
    public static class SourceDataType {
        public static final String PROPERTY_WRITE_POST_REPLY = "propertyWritePostReply";
        public static final String PROPERTY_READ_POST_REPLY = "propertyReadPostReply";
        public static final String DATA_GET_REPLY = "dataGetReply";
        public static final String ACTION_DOWN_RAW_REPLY = "actionDownRawReply";
        public static final String PROPERTY_POST = "propertyPost";
        public static final String DATA_POST = "dataPost";
        public static final String EVENT_UP_RAW = "eventUpRaw";

        public static final String WRITE_PROPERTIES = "写属性";
        public static final String READ_PROPERTIES = "读属性";
        public static final String DATA_CALL_TEST = "数据召测";
        public static final String ACTION_DISTRIBUTION = "动作下发";
        public static final String ATTRIBUTE_UPLOAD = "属性上传";
        public static final String DATA_UPLOAD = "数据上传";
        public static final String EVENT_UPLOAD = "事件上传";

        public static final String DATA_ID = "_id";
        public static final String DATA_SN = "sn";
        public static final String DATA_DT = "dt";
        public static final String DATA_DATATYPE = "datatype";
        public static final String DATA_DATAMSG = "datamsg";
    }

    /***
     *  物模型选项卡类别
     * @author suman
     * @Date  20:12
     */
    public static class ModelTab{
        public static final String PROPERTY_FLAG = "1";
        public static final String DATA_FLAG = "2";
        public static final String ACTION_FLAG = "3";
        public static final String EVENT_FLAG = "4";
        public static final String PROTOCOL_FLAG = "5";
    }
    /***
     * 物模型 属性、数据 数据类型
     * @author suman
     * @Date  20:13
     */
    public static class ModelDataType{
        public static final String BOOL="bool";
        public static final String INT="int32";
        public static final String ENUM="enum";
        public static final String TEXT="text";
        public static final String STRUCT="struct";
        public static final String ARRAY="array";
        public static final String UNSIGNED_INT="unsigned int";
        public static final String FLOAT="float";
        public static final String DOUBLE="double";
        public static final String OUTPUT="0";
        public static final String INPUT="1";
    }

    public static class ModelType{
        public static final String DIRECT="1";
        public static final String MIDDLE="2";
        public static final String SUBSET="3";

    }

    public static class Protocol{
        public static final String MQTT="1";
        public static final String MODBUS="2";
        public static final String TC="3";

    }
}
