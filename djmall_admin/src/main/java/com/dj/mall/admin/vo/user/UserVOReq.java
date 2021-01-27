package com.dj.mall.admin.vo.user;

import lombok.Data;

import java.util.List;

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
     *  用户性别 ：MAN为男，WOMAN为女
     */
    private String userSex;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户ID数组
     */
    private Integer[] ids;
    //private List<Integer> ids;

}
