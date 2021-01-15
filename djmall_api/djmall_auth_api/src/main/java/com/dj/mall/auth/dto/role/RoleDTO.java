package com.dj.mall.auth.dto.role;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author WYQ
 * @Date 2021/1/14 15:27
 * 角色实体类 -> dto
 */

@Data
public class RoleDTO implements Serializable {

    /**
     * 角色id
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String roleName;

}
