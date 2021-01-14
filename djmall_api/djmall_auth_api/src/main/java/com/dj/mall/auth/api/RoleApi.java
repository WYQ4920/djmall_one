package com.dj.mall.auth.api;

import com.dj.mall.auth.dto.RoleDTO;
import com.dj.mall.common.base.PageResult;
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

}
