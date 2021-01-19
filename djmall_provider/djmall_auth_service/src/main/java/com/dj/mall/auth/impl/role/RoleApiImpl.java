package com.dj.mall.auth.impl.role;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.auth.api.res.ResourceApi;
import com.dj.mall.auth.api.role.RoleApi;
import com.dj.mall.auth.service.user.UserRoleService;
import com.dj.mall.auth.entity.role.RoleResourceEntity;
import com.dj.mall.auth.entity.user.UserRoleEntity;
import com.dj.mall.auth.service.role.RoleResourceService;
import com.dj.mall.auth.dto.res.ResourceDTO;
import com.dj.mall.auth.dto.role.RoleDTO;
import com.dj.mall.auth.dto.role.TreeDataDTO;
import com.dj.mall.auth.entity.role.RoleEntity;
import com.dj.mall.auth.mapper.role.RoleMapper;
import com.dj.mall.common.base.ResultModel;
import com.dj.mall.common.constant.SystemConstant;
import com.dj.mall.common.util.DozerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author WYQ
 * @Date 2021/1/14 15:27
 * 角色表 -> api实现类
 */

@Service
public class RoleApiImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleApi {

    @Autowired
    private ResourceApi resourceApi;

    @Autowired
    private RoleResourceService roleResourceService;

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 角色展示
     */
    @Override
    public List<RoleDTO> findRoleAll(RoleDTO roleDTO) throws Exception {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (!StringUtils.isEmpty(roleDTO.getRoleName())) {
            queryWrapper.like("role_name", roleDTO.getRoleName());
        }
        List<RoleEntity> roleEntityList = super.list(queryWrapper);
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
        if (null != roleEntity && !roleEntity.getId().equals(roleDTO.getId())) {
            return new ResultModel().error(SystemConstant.ERROR_CODE, "该角色已存在");
        }
        super.updateById(DozerUtil.map(roleDTO, RoleEntity.class));
        return new ResultModel().success();
    }

    /**
     * 删除角色
     *
     * @param id 角色id
     * @return
     * @throws Exception
     */
    @Override
    public boolean deleteRole(Integer id) throws Exception {
        // 用户角色表
        QueryWrapper<UserRoleEntity> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("role_id", id);
        List<UserRoleEntity> list = userRoleService.list(queryWrapper1);
        if (SystemConstant.NUMBER != list.size()) {
            return false;
        }
        userRoleService.remove(queryWrapper1);
        // 角色表
        super.removeById(id);
        // 角色资源表
        QueryWrapper<RoleResourceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", id);
        roleResourceService.remove(queryWrapper);
        return true;
    }

    /**
     * 展示关联资源
     *
     * @param roleDTO 角色资源
     * @return
     * @throws Exception
     */
    @Override
    public List<TreeDataDTO> findAll(RoleDTO roleDTO) throws Exception {
        // 获取资源表全部信息
        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setIsDel(0);
        List<ResourceDTO> resourceDTOList = resourceApi.findAll1(resourceDTO);
        // 根据角色id获取角色对应的所有资源
        QueryWrapper<RoleResourceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleDTO.getId());
        List<RoleResourceEntity> resourceEntities = roleResourceService.list(queryWrapper);
        // List<RoleResourceDTO> roleResourceDTOS = roleResourceService.findRoleResourceByRoleId(roleDTO.getId());
        // 定义ztree集合
        List<TreeDataDTO> treeDataList = new ArrayList<>();
        // 组装数据
        for (ResourceDTO res : resourceDTOList) {
            TreeDataDTO treeDataDTO = TreeDataDTO.builder()
                    .id(res.getId())
                    .parentId(res.getParentId())
                    .resourceName(res.getResourceName())
                    .build();
            for (RoleResourceEntity rrs : resourceEntities) {
                if (rrs.getResourceId().equals(res.getId())) {
                    treeDataDTO.setChecked(true);
                    break;
                }
            }
            // 组装数据添加到集合中
            treeDataList.add(treeDataDTO);
        }
        return treeDataList;
    }

    /**
     * 保存角色资源
     *
     * @param roleDTO 角色资源
     * @throws Exception
     */
    @Override
    public void saveRoleResource(RoleDTO roleDTO) throws Exception {
        // 删除数据库已有数据
        QueryWrapper<RoleResourceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleDTO.getId());
        roleResourceService.remove(queryWrapper);
        // 定义角色资源表集合
        List<RoleResourceEntity> resourceEntities = new ArrayList<>();
        // 添加数据
        for (Integer resourceIds : roleDTO.getResourceIds()) {
            resourceEntities.add(RoleResourceEntity.builder()
                    .roleId(roleDTO.getId())
                    .resourceId(resourceIds)
                    .build());
        }
        roleResourceService.saveBatch(resourceEntities);
    }

    /**
     * 获取角色集合
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<RoleDTO> getRoleList() throws Exception {
        return DozerUtil.mapList(super.list(), RoleDTO.class);
    }

}
