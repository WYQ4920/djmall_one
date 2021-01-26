package com.dj.mall.auth.mapper.role;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.mall.auth.dto.res.ResourceDTO;
import com.dj.mall.auth.entity.resource.ResourceEntity;
import com.dj.mall.auth.entity.role.RoleEntity;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @Author WYQ
 * @Date 2021/1/14 15:28
 * 角色表 -> 持久层接口
 */
public interface RoleMapper extends BaseMapper<RoleEntity> {

    /**
     * 根据角色id 获取角色资源
     * @param roleId 角色id
     * @return
     * @throws DataAccessException
     */
    List<ResourceEntity> getRoleResourceByRoleId(Integer roleId) throws DataAccessException;
}
