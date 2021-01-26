package com.dj.mall.admin.web.dict.attr;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.dict.api.attr.ProductAttrApi;
import com.dj.mall.dict.dto.attr.ProductAttrDTO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author WYQ
 * @Date 2021/1/24 12:01
 */

@Controller
@RequestMapping("/product/attr/")
public class ProductAttrPageController {

    @Reference
    private ProductAttrApi productAttrApi;

    /**
     * 去展示商品属性表
     * @return
     */
    @GetMapping("toList")
    @RequiresPermissions("PRODUCT_ATTRIBUTE_MAINTENANCE_MANAGER")
    public String toList() {
        return "/dict/attr/attr_list";
    }

    /**
     * 去展示商品属性值表
     */
    @GetMapping("toRelevance")
    public String toRelevance(Integer id, ModelMap map) throws Exception {
        ProductAttrDTO productAttrDTO = productAttrApi.findAttrById(id);
        map.put("list", productAttrDTO);
        return "/dict/attr/attr_value_list";
    }
}
