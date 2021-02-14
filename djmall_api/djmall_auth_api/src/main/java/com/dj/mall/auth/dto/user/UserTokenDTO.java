package com.dj.mall.auth.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author zhengyk
 * @Date 2021/2/4 19:55
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserTokenDTO implements Serializable {

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
