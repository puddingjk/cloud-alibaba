package com.wanwei.common.redis.constant;

/**
 * @ClassName : RedisKey
 * @Description :
 * @Author : LuoHongyu
 * @Date : 2021-10-23 14:51
 */
public interface RedisKey {

     /**
      * 微服务redis存储的key值
      */
     String SER_AUTH = "auth:";
     String SER_AGG = "agg:";
     String SER_BILL = "bill:";
     String SER_CHAT = "chat:";
     String SER_MES = "message:";
     String SER_SERVER = "server:";
     String SER_TAX = "tax:";

     /**
      * 其余通用的key
      */
     String ACCESS_TOKEN_KEY = "accessToken:";


}
