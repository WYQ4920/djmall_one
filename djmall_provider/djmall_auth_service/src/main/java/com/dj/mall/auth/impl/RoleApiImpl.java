package com.dj.mall.auth.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.auth.api.RoleApi;
import com.dj.mall.auth.dto.RoleDTO;
import com.dj.mall.auth.entity.RoleEntity;
import com.dj.mall.auth.mapper.RoleMapper;
import com.dj.mall.common.base.ResultModel;
import com.dj.mall.common.base.SystemConstant;
import com.dj.mall.common.util.DozerUtil;

import java.util.List;

/**
 * @Author WYQ
 * @Date 2021/1/14 15:27
 * 角色表 -> api实现类
 */

@Service
public class RoleApiImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleApi {

    /**
     * 角色展示
     */
    @Override
    public List<RoleDTO> findRoleAll() throws Exception {
        List<RoleEntity> roleEntityList = super.list();
        return DozerUtil.mapList(roleEntityList, RoleDTO.class);
    }

    /**
     * 新增角色查重
     */
    @Override
    public Boolean findRoleByRoleName(String roleName) throws Exception {
        QueryWrapper<RoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_name", roleName);
        RoleEntity roleEntity = super.getOne(queryWrapper);
        return null == roleEntity ? true : false;
    }

    /**
     * 新增角色
     */
    @Override
    public void addRole(RoleDTO roleDTO) throws Exception {
        super.save(DozerUtil.map(roleDTO, RoleEntity.class));
    }

    /**
     * 修改角色查询
     */
    @Override
    public RoleDTO findRoleById(Integer id) throws Exception {
        return DozerUtil.map(super.getById(id), RoleDTO.class);
    }

    /**
     * 修改角色
     */
    @Override
    public ResultModel updateRole(RoleDTO roleDTO) throws Exception {
        RoleEntity roleEntity = super.getById(roleDTO.getId());
        if (null != roleEntity && roleEntity.getId().equals(roleDTO.getId())) {
            return new ResultModel().error(SystemConstant.ERROR_CODE, "该角色已存在");
        }
        super.updateById(DozerUtil.map(roleDTO, RoleEntity.class));
        return new ResultModel().success();
    }

}
