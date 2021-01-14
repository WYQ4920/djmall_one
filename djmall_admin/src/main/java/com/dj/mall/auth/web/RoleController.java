package com.dj.mall.auth.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.api.RoleApi;
import com.dj.mall.auth.dto.RoleDTO;
import com.dj.mall.auth.vo.RoleVOReq;
import com.dj.mall.common.base.ResultModel;
import com.dj.mall.common.util.DozerUtil;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author WYQ
 * @Date 2021/1/14 15:10
 * 角色表 —> 操作控制层
 */

@RestController
@RequestMapping("/role/")
public class RoleController {

    @Reference(check = false)
    private RoleApi roleApi;

    /**
     * 角色展示
     */
    @GetMapping("show")
    public ResultModel show() throws Exception {
        List<RoleDTO> roleDTOS = roleApi.findRoleAll();
        return new ResultModel<>().success(roleDTOS);
    }

    /**
     * 新增角色查重
     */
    @GetMapping("checkRoleName")
    public Boolean checkRoleName(String roleName) throws Exception {
        Boolean result = roleApi.findRoleByRoleName(roleName);
        return result;
    }

    /**
     * 新增角色
     */
    @PostMapping("add")
    public ResultModel add(RoleVOReq roleVOReq) throws Exception {
        Assert.hasText(roleVOReq.getRoleName(), "角色名不能为空");
        roleApi.addRole(DozerUtil.map(roleVOReq, RoleDTO.class));
        return new ResultModel().success();
    }

    /**
     * 修改角色
     */
    @PutMapping("update")
    public ResultModel update(RoleVOReq roleVOReq) throws Exception {
        Assert.hasText(roleVOReq.getRoleName(), "角色名不能为空");
        roleApi.updateRole(DozerUtil.map(roleVOReq, RoleDTO.class));
        return new ResultModel().success();
    }

}
