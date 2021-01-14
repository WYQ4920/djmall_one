package com.dj.mall.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author WYQ
 * @Date 2021/1/14 15:27
 * 角色实体类
 */

@Data
@TableName("djmall_auth_role")
public class RoleEntity {

    /**
     * 角色id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 角色名称
     */
    private String roleName;

}
