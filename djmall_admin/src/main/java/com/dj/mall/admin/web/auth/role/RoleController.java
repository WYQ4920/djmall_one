package com.dj.mall.admin.web.auth.role;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.role.RoleVOReq;
import com.dj.mall.admin.vo.role.RoleVOResp;
import com.dj.mall.admin.vo.role.TreeDataVOResp;
import com.dj.mall.auth.api.role.RoleApi;
import com.dj.mall.auth.dto.role.RoleDTO;
import com.dj.mall.auth.dto.role.TreeDataDTO;
import com.dj.mall.common.base.ResultModel;
import com.dj.mall.common.util.DozerUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author WYQ
 * @Date 2021/1/14 15:10
 * 角色表 —> 操作控制层
 */

@RestController
@RequestMapping("/auth/role/")
public class RoleController {

    @Reference
    private RoleApi roleApi;

    /**
     * 角色展示
     */
    @GetMapping("show")
    @RequiresPermissions("ROLE_MANAGER")
    public ResultModel show(RoleVOReq roleVOReq) throws Exception {
        List<RoleDTO> roleDTOS = roleApi.findRoleAll(DozerUtil.map(roleVOReq, RoleDTO.class));
        return new ResultModel<>().success(DozerUtil.mapList(roleDTOS, RoleVOResp.class));
    }

    /**
     * 新增角色查重
     */
    @PostMapping("checkRoleName")
    public Boolean checkRoleName(String roleName) throws Exception {
        Boolean result = roleApi.findRoleByRoleName(roleName);
        return result;
    }

    /**
     * 新增角色
     */
    @PostMapping("add")
    @RequiresPermissions("ROLE_ADD_BTN")
    public ResultModel add(RoleVOReq roleVOReq) throws Exception {
        Assert.hasText(roleVOReq.getRoleName(), "角色名不能为空");
        roleApi.addRole(DozerUtil.map(roleVOReq, RoleDTO.class));
        return new ResultModel().success();
    }

    /**
     * 修改角色
     */
    @PostMapping("update")
    @RequiresPermissions("ROLE_UPDATE_BTN")
    public ResultModel update(RoleVOReq roleVOReq) throws Exception {
        Assert.hasText(roleVOReq.getRoleName(), "角色名不能为空");
        return roleApi.updateRole(DozerUtil.map(roleVOReq, RoleDTO.class));
    }

    /**
     * 删除角色
     */
    @PostMapping("del")
    @RequiresPermissions("ROLE_DEL_BTN")
    public ResultModel del(Integer id) throws Exception {
        boolean result = roleApi.deleteRole(id);
        if (result) {
            return new ResultModel().success();
        }
        return new ResultModel().error("角色已有关联用户, 无法删除");
    }

    /**
     * 展示关联资源
     */
    @GetMapping("showResRel")
    @RequiresPermissions("ROLE_RELEVANCE_RESOURCE_BTN")
    public ResultModel showResRel(RoleVOReq roleVOReq) throws Exception {
        List<TreeDataDTO> treeDataDTOS = roleApi.findAll(DozerUtil.map(roleVOReq, RoleDTO.class));
        return new ResultModel().success(DozerUtil.mapList(treeDataDTOS, TreeDataVOResp.class));
    }

    /**
     * 保存角色资源
     */
    @PostMapping("saveRoleResource")
    public ResultModel saveRoleResource(RoleVOReq roleVOReq) throws Exception {
        roleApi.saveRoleResource(DozerUtil.map(roleVOReq, RoleDTO.class));
        return new ResultModel().success();
    }
}
