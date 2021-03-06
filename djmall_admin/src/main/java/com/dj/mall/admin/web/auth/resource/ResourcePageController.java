package com.dj.mall.admin.web.auth.resource;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.api.res.ResourceApi;
import com.dj.mall.auth.dto.res.ResourceDTO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/res/")
public class ResourcePageController {

    @Reference(check = false)
    private ResourceApi resourceApi;

    @RequestMapping("toShowResZtree")
    @RequiresPermissions("RESOURCE_MANAGER")
    public String toShowResZtree() throws Exception {
        return "auth/resource/show";
    }

    @RequestMapping("/toAdd/{id}")
    @RequiresPermissions("RESOURCE_ADD_BTN")
    public String toAdd(@PathVariable Integer id, Model model) throws Exception {
        if (id == 0) {
            model.addAttribute("resourceName", "顶级");
        } else {
            ResourceDTO resourceDTO = resourceApi.findResById(id);
            model.addAttribute("resourceName", resourceDTO.getResourceName());
        }
        model.addAttribute("parentId", id);
        return "auth/resource/add";
    }

}



