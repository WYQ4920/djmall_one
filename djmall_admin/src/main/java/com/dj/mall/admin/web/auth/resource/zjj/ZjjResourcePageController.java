package com.dj.mall.admin.web.auth.resource.zjj;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.api.res.ZjjResourceApi;
import com.dj.mall.auth.dto.res.ResourceDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/zjj/resource/")
public class ZjjResourcePageController {

    public static final String PAGE_PREFIX = "/auth/resource/zjj/";

    @Reference
    private ZjjResourceApi zjjResourceApi;

    @RequestMapping("toShow")
    public String toShow() {
        return PAGE_PREFIX + "resource_list";
    }

    @RequestMapping("toAdd/{parentId}")
    public String toAdd(@PathVariable Integer parentId, ModelMap map) throws Exception {
        if (parentId != 0) {
            ResourceDTO resourceDTO = zjjResourceApi.getResourceById(parentId);
            map.put("resourceName", resourceDTO.getResourceName());
        } else {
            map.put("resourceName", "顶级");
        }
        map.put("parentId", parentId);
        return PAGE_PREFIX + "resource_add";
    }


}
