package com.dj.mall.platform.vo.role;

import lombok.Data;

/**
 * @Author WYQ
 * @Date 2021/1/14 16:54
 * 角色实体类 -> voResp
 */

@Data
public class RoleVOResp {

    /**
     * 角色id
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String roleName;
}
