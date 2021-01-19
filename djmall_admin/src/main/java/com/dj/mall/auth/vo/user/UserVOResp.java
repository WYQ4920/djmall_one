package com.dj.mall.auth.vo.user;

import lombok.Data;

@Data
public class UserVOResp {

    /**
     * 用户ID
     */
    private Integer id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 角色昵称
     */
    private String nickName;

}
