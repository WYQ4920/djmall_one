package com.dj.mall.auth.web.role.page;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.api.role.RoleApi;
import com.dj.mall.auth.dto.role.RoleDTO;
import com.dj.mall.auth.vo.role.RoleVOResp;
import com.dj.mall.common.util.DozerUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author WYQ
 * @Date 2021/1/14 15:02
 * 角色表 -> 操作跳转路径控制层
 */

@Controller
@RequestMapping("/auth/role/")
public class RolePageController {

    @Reference
    private RoleApi roleApi;

    /**
     * 去展示
     */
    @RequiresPermissions("ROLE_MANAGER")
    @GetMapping("toShow")
    public String toShow() {
        return "auth/role/role_show";
    }

    /**
     * 去新增
     */
    @RequiresPermissions("ROLE_ADD_BTN")
    @GetMapping("toAdd")
    public String toAdd() {
        return "auth/role/role_add";
    }

    /**
     * 去修改
     */
    @GetMapping("toUpdate")
    @RequiresPermissions("ROLE_UPDATE_BTN")
    public String toUpdate(Integer id, ModelMap map) throws Exception {
        RoleDTO role = roleApi.findRoleById(id);
        map.put("role", DozerUtil.map(role, RoleVOResp.class));
        return "auth/role/role_update";
    }

    /**
     * 去展示关联资源
     */
    
    @GetMapping("toResRelZtree")
    public String toResRelZtree(Integer id, ModelMap map) {
        map.put("id", id);
        return "auth/role/role_resource";
    }

}
