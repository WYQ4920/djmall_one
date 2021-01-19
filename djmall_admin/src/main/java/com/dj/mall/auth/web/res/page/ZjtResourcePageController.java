package com.dj.mall.auth.web.res.page;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.api.res.ZjtResourceApi;
import com.dj.mall.auth.dto.res.ResourceDTO;
import com.dj.mall.common.constant.SystemConstant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/zjt/")
public class ZjtResourcePageController {

    @Reference
    private ZjtResourceApi zjtResourceApi;

    /**
     * 去资源展示
     *
     * @return
     */
    @GetMapping("toZtreeShow")
    public String toZtreeShow() {
        return "auth/zresource/resource_show";
    }

    /**
     * 获取资源id
     *
     * @param parentId
     * @param modelMap
     * @return
     * @throws Exception
     */
    @GetMapping("toAdd/{parentId}")
    public String toAdd(@PathVariable Integer parentId, ModelMap modelMap) throws Exception {
        //parentId  上级节点的id
        if (parentId != SystemConstant.ERROR_ONE) {
            ResourceDTO parentResource = zjtResourceApi.findResouceBySuperId(parentId);
            modelMap.put("parentResourceName", parentResource.getResourceName());
        } else {
            modelMap.put("parentId", "顶级");
        }
        modelMap.put("parentId", parentId);
        return "auth/zresource/resource_add";
    }
}
