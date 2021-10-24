package com.wanwei.common.base.constants;

/**
 * CommonConstants
 * @author qbk
 * @version 1.0 2018/6/13
 * @since 1.0
 */
public class CommonConstants {


    public final static String REDIS_USER_LOG_KEY = "user:logList";
    public final static String RESOURCE_TYPE_MENU = "menu";
    public final static String RESOURCE_TYPE_BTN = "button";
    // 用户token异常
    public static final Integer EX_USER_INVALID_CODE = 40101;
    public static final Integer EX_USER_PASS_INVALID_CODE = 40001;
    // 客户端token异常
    public static final Integer EX_CLIENT_INVALID_CODE = 40301;
    public static final Integer EX_CLIENT_FORBIDDEN_CODE = 40331;
    public static final Integer EX_OTHER_CODE = 500;

    public static final String EX_COMMON_MES = "服务器端异常!";

    public static final String CONTEXT_KEY_USER_ID = "currentUserId";
    public static final String CONTEXT_KEY_USERNAME = "currentUserName";
    public static final String CONTEXT_KEY_USER_NAME = "currentUser";
    public static final String CONTEXT_KEY_USER_TOKEN = "currentUserToken";
    public static final String JWT_KEY_USER_ID = "userId";
    public static final String JWT_KEY_NAME = "name";

    public static final class OP {
        private OP() {
        }
        public static final String  LIKE = "like" ;
        public static final String LEFT_LIKE = "leftLike";
        public static final String RIGHT_LIKE = "rightLike";
        public static final String EQ = "eq";// 等于
        public static final String NE = "ne";// 不等于
        public static final String GT = "gt";// 大于
        public static final String NL = "nl";// 大于等于
        public static final String LT = "lt";// 小于
        public static final String NG = "ng";// 小于等于
        public static final String NULL = "null";// 值为 null
        public static final String NOTNULL = "notNull";// 值不为 null
        public static final String IN = "in";// in 操作
        public static final String NOTIN = "notIn";// 不在区间 操作
        public static final String  BETWEEN = "between";// between 操作
        public static final String NOTBETWEEN ="notBetween";// 不在区间 操作
        public static final String ORDER ="ody";// 排序

    }
    public static final String OFFICER_TABLE_NAME = "td_monitor_task_";
    public static final String OFFICER_TABLE_DETAIL_NAME = "td_monitor_task_details_";

}
