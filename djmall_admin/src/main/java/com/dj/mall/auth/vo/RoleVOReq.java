package com.dj.mall.auth.vo;

import lombok.Data;

/**
 * @Author WYQ
 * @Date 2021/1/14 16:54
 * 角色实体类 -> voReq
 */

@Data
public class RoleVOReq {

    /**
     * 角色id
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String roleName;
}
