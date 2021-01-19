package com.dj.mall.auth.web.res;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.api.res.ResourceApi;
import com.dj.mall.auth.dto.res.ResourceDTO;
import com.dj.mall.auth.vo.resource.ResourceVOResp;
import com.dj.mall.common.base.BusinessException;
import com.dj.mall.common.constant.SystemConstant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/res/")
public class ResourcePageController {

    @Reference(check = false)
    private ResourceApi resourceApi;

    @RequestMapping("toShowResZtree")
    public String toShowResZtree() throws Exception {
        return "res/show";
    }

    @RequestMapping("/toAdd/{id}")
    public String toAdd(@PathVariable Integer id, Model model) throws Exception {
        if (id == SystemConstant.ERROR_ONEE) {
            model.addAttribute("resourceName", "顶级");
        } else {
            ResourceDTO resourceDTO = resourceApi.findResById(id);
            model.addAttribute("resourceName", resourceDTO.getResourceName());
        }
        model.addAttribute("parentId", id);
        return "res/add";
    }


}



