package com.wanwei.cbase.common.constants;


/**
 * GlobalConstant
 *
 * @author qbk
 * @version 1.0 2018/6/13
 * @since 1.0
 */
public class GlobalConstant {


    public static void main(String[] args) {
        int error4001 = Constants.Token.ERROR_4001;
    }

    public static final long FILE_MAX_SIZE = 5L * 1024 * 1024;
    public static final String UNKNOWN = "unknown";

    public static final String X_FORWARDED_FOR = "X-Forwarded-For";
    public static final String X_REAL_IP = "X-Real-IP";
    public static final String PROXY_CLIENT_IP = "Proxy-Client-IP";
    public static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
    public static final String HTTP_CLIENT_IP = "HTTP_CLIENT_IP";
    public static final String HTTP_X_FORWARDED_FOR = "HTTP_X_FORWARDED_FOR";
    public static final String MH = ":";
    public static final int MAX_IP_LENGTH = 15;

    public static final int TWO_INT = 2;
    public static final int M_SIZE = 1024;

    /**
     * 系统常量
     */
    public static final class Sys {

        private Sys() {
        }

        /**
         * 全局用户
         */
        public static final String TOKEN_AUTH_DTO = "CURRENT_USER_DTO";

        public static final String CURRENT_REQUEST_PORT = "CURRENT_REQUEST_PORT";

        public static final String FEIGN_REQ_HEADER = "FeignPort";
        //BaseController 日志
        public static final String BASE_ADD = "BASE_ADD";
        public static final String BASE_UPDATE = "BASE_UPDATE";
        public static final String BASE_REMOVE = "BASE_REMOVE";
    }

}
