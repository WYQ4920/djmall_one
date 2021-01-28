package com.dj.mall.admin.web.product;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.common.constant.ProductConstant;
import com.dj.mall.dict.api.DictApi;
import com.dj.mall.dict.dto.DictDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author WYQ
 * @Date 2021/1/27 16:45
 */

@Controller
@RequestMapping("/product/")
public class ProductPageController {

    @Reference
    private DictApi dictApi;

    /**
     * 去展示商品
     * @return
     */
    @RequestMapping("toList")
    public String toList() {
        return "/product/product_list";
    }

    /**
     * 去新增商品
     */
    @RequestMapping("toAdd")
    public String toAdd(ModelMap map) throws Exception {
        List<DictDTO> productTypeList = dictApi.findByCode(ProductConstant.PRODUCT_TYPE);
        map.put("list", productTypeList);
        return "/product/product_add";
    }

    /**
     * 去新增商品属性
     */
    @RequestMapping("toAddAttr")
    public String toAddAttr() {
        return "/product/product_add_attr";
    }

}
