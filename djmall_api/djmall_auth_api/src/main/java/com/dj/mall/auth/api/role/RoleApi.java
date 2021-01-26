package com.dj.mall.auth.api.role;

import com.dj.mall.auth.dto.res.ResourceDTO;
import com.dj.mall.auth.dto.role.RoleDTO;
import com.dj.mall.auth.dto.role.TreeDataDTO;
import com.dj.mall.common.base.ResultModel;

import java.util.List;

/**
 * @Author WYQ
 * @Date 2021/1/14 15:26
 * 角色表 -> api接口
 */
public interface RoleApi {

    /**
     * 角色展示
     */
    List<RoleDTO> findRoleAll(RoleDTO roleDTO) throws Exception;

    /**
     * 新增角色查重
     */
    Boolean findRoleByRoleName(String roleName) throws Exception;

    /**
     * 新增角色
     */
    void addRole(RoleDTO roleDTO) throws Exception;

    /**
     * 修改角色查询
     */
    RoleDTO findRoleById(Integer id) throws Exception;

    /**
     * 修改角色
     */
    ResultModel updateRole(RoleDTO roleDTO) throws Exception;

    /**
     * 删除角色
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteRole(Integer id) throws Exception;

    /**
     * 展示关联资源
     *
     * @param roleDTO 角色资源
     * @return
     * @throws Exception
     */
    List<TreeDataDTO> findAll(RoleDTO roleDTO) throws Exception;

    /**
     * 保存角色资源
     *
     * @param roleDTO 角色资源
     * @throws Exception
     */
    void saveRoleResource(RoleDTO roleDTO) throws Exception;

    /**
     * 获取角色集合
     *
     * @return
     * @throws Exception
     */
    List<RoleDTO> getRoleList() throws Exception;

    /**
     * 通过角色id 获取对应的资源集合
     * @param roleId 角色id
     * @return
     * @throws Exception
     */
    List<ResourceDTO> getRoleResource(Integer roleId) throws Exception;
}
