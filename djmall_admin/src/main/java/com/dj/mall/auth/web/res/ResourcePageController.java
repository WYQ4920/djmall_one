package com.dj.mall.auth.web.res;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.api.res.ResourceApi;
import com.dj.mall.auth.dto.res.ResourceDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
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
        Assert.hasText(String.valueOf(id), "请选择编辑资源");
        ResourceDTO one = resourceApi.findResById(id);
        model.addAttribute("one",one);
        return "res/update";
    }



}



