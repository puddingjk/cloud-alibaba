package com.wanwei.common.base.exception;


import com.wanwei.common.base.constants.CommonConstants;

/**
 * ServerCommonException
 * @author qbk
 * @version 1.0 2018/6/13
 * @since 1.0
 */
public class ServerCommonException extends BaseException {
    public ServerCommonException() {
        super(CommonConstants.EX_COMMON_MES, CommonConstants.EX_OTHER_CODE);
    }

    public ServerCommonException(Throwable cause) {
        super(CommonConstants.EX_COMMON_MES,cause);
    }
}
