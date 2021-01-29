package com.dj.mall.auth.bo.user;

import com.dj.mall.common.constant.UserConstant;
import lombok.Data;

/**
 * @author ZJJ
 * @date 2021/1/19 15:12
 */
@Data
public class UserBO {

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
     * 用户密码盐
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
     * 用户性别 ：MAN为男，WOMAN为女
     */
    private String userSex;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 用户性别展示
     */
    private String sexShow;

    /**
     * 用户角色ID
     */
    private Integer roleId;

    /**
     * 用户状态：NORMAL为正常，UNACTIVATED为未激活
     */
    private String userStatus;

    /**
     * 用户状态展示
     */
    private String statusShow;
}
