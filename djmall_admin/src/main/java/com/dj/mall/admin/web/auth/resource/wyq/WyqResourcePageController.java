package com.dj.mall.admin.web.auth.resource.wyq;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.api.res.WyqResourceApi;
import com.dj.mall.auth.dto.res.ResourceDTO;
import com.dj.mall.common.constant.SystemConstant;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author WYQ
 * @Date 2021/1/15 22:01
 * 资源表 -> 跳转路径控制层
 */

@Controller
@RequestMapping("/auth/resource/wyq/")
public class WyqResourcePageController {

    @Reference
    private WyqResourceApi wyqResourceApi;

    /**
     * 页面前缀
     */
    private static final String PAGE_PREFIX = "/auth/resource/wyq";

    /**
     * 去展示资源
     */
    @GetMapping("toList")
    @RequiresPermissions("WYQ_RESOURCE_MANAGER")
    public String toShow() {
        return PAGE_PREFIX + "/resource_list";
    }

    /**
     * 去新增资源
     */
    @GetMapping("toAdd")
    @RequiresPermissions("WYQ_RESOURCE_ADD_BTN")
    public String toAdd(Integer parentId, ModelMap map) throws Exception {
        if (parentId != SystemConstant.PARENT_ID) {
            ResourceDTO resourceDTO = wyqResourceApi.getResource(parentId);
            if (resourceDTO != null) {
                // parentId -> 上级id
                map.put("resourceName", resourceDTO.getResourceName());
            } else {
                map.put("resourceName", "顶级");
            }
        } else {
            map.put("resourceName", "顶级");
        }
        map.put("parentId", parentId);
        return PAGE_PREFIX + "/resource_add";
    }

}
