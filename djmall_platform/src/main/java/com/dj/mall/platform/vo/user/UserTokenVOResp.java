package com.dj.mall.platform.vo.user;


import lombok.Data;

/**
 * @Author zhengyk
 * @Date 2021/2/4 19:55
 */
@Data
public class UserTokenVOResp{

    /**
     * 用户ID
     */
    private Integer id;

    /**
     * 角色昵称
     */
    private String nickName;

    /**
     * 用户名/手机号/邮箱
     */
    private String  userNPE;

    /**
     * 用户名
     */
    private String userName;

    /**
     * token
     */
    private String token;

}
