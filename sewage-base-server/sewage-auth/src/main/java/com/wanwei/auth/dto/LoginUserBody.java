package com.wanwei.auth.dto;

import lombok.Data;

/**
 * @ClassName : LoginUserDTO
 * @Description :
 * @Author : LuoHongyu
 * @Date : 2021-10-24 20:17
 */
@Data
public class LoginUserBody {

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;
}
