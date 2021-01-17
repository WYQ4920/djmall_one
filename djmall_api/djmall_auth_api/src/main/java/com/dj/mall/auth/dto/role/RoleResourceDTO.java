package com.dj.mall.auth.dto.role;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author WYQ
 * @Date 2021/1/17 21:27
 */

@Data
public class RoleResourceDTO implements Serializable {

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 资源id
     */
    private Integer resourceId;
}
