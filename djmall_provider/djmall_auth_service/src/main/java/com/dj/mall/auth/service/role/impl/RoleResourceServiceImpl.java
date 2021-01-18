package com.dj.mall.auth.service.role.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.auth.dto.role.RoleResourceDTO;
import com.dj.mall.auth.entity.role.RoleResourceEntity;
import com.dj.mall.auth.mapper.role.RoleResourceMapper;
import com.dj.mall.auth.service.role.RoleResourceService;
import com.dj.mall.common.util.DozerUtil;

import java.util.List;

/**
 * @Author WYQ
 * @Date 2021/1/17 21:31
 */

@Service
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceMapper, RoleResourceEntity> implements RoleResourceService {

    /**
     * 查询角色对应的所有资源
     * @param roleId 角色id
     * @return
     * @throws Exception
     */
    @Override
    public List<RoleResourceDTO> findRoleResourceByRoleId(Integer roleId) throws Exception {
        QueryWrapper<RoleResourceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        return DozerUtil.mapList(super.list(queryWrapper), RoleResourceDTO.class);
    }
}
