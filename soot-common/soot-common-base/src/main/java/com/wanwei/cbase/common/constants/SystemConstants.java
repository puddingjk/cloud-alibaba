package com.wanwei.cbase.common.constants;

/**
 * @ClassName : SystemConstants
 * @Description : 系统参数
 * @Author : LuoHongyu
 * @Date: 2021-08-07 13:15
 */
public class SystemConstants {

    /**
     * 用于拼接数据库语句动态参数
     */
    public static class Condition {
        public static final String LIKE = "like";
        public static final String LEFT_LIKE = "lLike";
        public static final String RIGHT_LIKE = "rLike";
        public static final String NOT_LIKE = "nLike";
        public static final String NOT_LEFT_LIKE = "nLeftLike";
        public static final String NOT_RIGHT_LIKE = "nRLike";
        public static final String EQ = "eq";
        public static final String NE = "ne";
        public static final String GT = "gt";
        public static final String GE = "ge";
        public static final String LT = "lt";
        public static final String LE = "le";
        public static final String NULL = "null";
        public static final String NOTNULL = "notNull";
        public static final String IN = "in";
        public static final String NOT_IN = "notIn";
        public static final String BETWEEN = "between";
        public static final String NOT_BETWEEN = "notBetween";
        public static final String ORDER_ASC = "oAsc";
        public static final String ORDER_DES = "oDes";
    }
}
