package com.dj.mall.auth.service.role;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.mall.auth.dto.role.RoleResourceDTO;
import com.dj.mall.auth.entity.role.RoleResourceEntity;

import java.util.List;

/**
 * @Author WYQ
 * @Date 2021/1/17 21:30
 */
public interface RoleResourceService extends IService<RoleResourceEntity> {

    /**
     * 查询角色对应的所有资源
     * @param roleId 角色id
     * @return
     * @throws Exception
     */
    List<RoleResourceDTO> findRoleResourceByRoleId(Integer roleId) throws Exception;
}
