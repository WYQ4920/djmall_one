package com.dj.mall.auth.entity.user;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author WYQ
 * @Date 2021/1/18 1:41
 */

@Data
@Accessors(chain = true)
@TableName("djmall_auth_user_role")
public class UserRoleEntity {

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 角色id
     */
    private Integer roleId;
}
