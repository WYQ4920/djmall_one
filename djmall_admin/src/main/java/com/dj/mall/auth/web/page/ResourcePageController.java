package com.dj.mall.auth.web.page;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.api.ResourceApi;
import com.dj.mall.auth.dto.ResourceDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping("toAdd")
    public String toAdd(Integer parentId,Model model) throws Exception {
        model.addAttribute("parentId",parentId);
        return "res/add";
    }

    @RequestMapping("toUpdate")
    public String toUpdate(Model model,Integer id) throws Exception {
        ResourceDTO one = resourceApi.findById(id);
        model.addAttribute("one",one);
        return "res/update";
    }



}



