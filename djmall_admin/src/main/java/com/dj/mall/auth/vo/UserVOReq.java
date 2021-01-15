package com.dj.mall.auth.vo;

import lombok.Data;

@Data
public class UserVOReq {

    /**
     * 用户ID
     */
    private Integer id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPwd;

}
