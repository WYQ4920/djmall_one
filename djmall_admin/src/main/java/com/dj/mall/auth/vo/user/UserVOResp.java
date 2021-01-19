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

    /**
     * 用户盐
     */
    private String salt;

    /**
     * 用户手机号
     */
    private String userPhone;

    /**
     * 用户邮箱
     */
    private String userEmail;

    /**
     * 用户性别 ：1为男，2为女
     */
    private Integer userSex;

    /**
     * 角色ID
     */
    private Integer roleId;

}
