package com.dj.mall.auth.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户实体类
 * @author dj
 */
@Data
@TableName("djmall_auth_user")
public class UserEntity {

    /**
     * 用户ID
     */
    @TableId(type = IdType.AUTO)
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
     *  用户邮箱
     */
    private String userEmail;

    /**
     *  用户性别 ：1为男，2为女
     */
    private Integer userSex;

    /**
     * 用户昵称
     */
    private String nickName;

}
