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

    /**
     *  角色名称
     */
    private String roleName;

    /**
     * 用户性别展示
     */
    private String sexShow;


    public String getSexShow() {
        if (userSex == UserConstant.SEX_NAM){
            return "男";
        }
        if (userSex == UserConstant.SEX_WONAM){
            return "女";
        }
        return "";
    }
}
