package com.dj.mall.auth.dto;

import lombok.Data;

/**
 * @Author WYQ
 * @Date 2021/1/14 15:27
 * 角色实体类
 */

@Data
public class RoleDTO {

    /**
     * 角色id
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String roleName;

}
