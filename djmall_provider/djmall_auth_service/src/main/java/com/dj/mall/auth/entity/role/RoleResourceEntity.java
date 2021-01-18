package com.dj.mall.auth.entity.role;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author WYQ
 * @Date 2021/1/17 21:25
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("djmall_auth_role_resource")
public class RoleResourceEntity {

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 资源id
     */
    private Integer resourceId;
}
