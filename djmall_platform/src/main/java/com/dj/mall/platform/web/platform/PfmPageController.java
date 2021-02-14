package com.dj.mall.platform.web.platform;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.common.constant.ProductConstant;
import com.dj.mall.dict.api.DictApi;
import com.dj.mall.dict.api.freight.FreightApi;
import com.dj.mall.dict.dto.DictDTO;
import com.dj.mall.product.api.ProductApi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/djmall_platform/")
@Controller
public class PfmPageController {

    @Reference
    private ProductApi productApi;

    @Reference
    private DictApi dictApi;

    @Reference
    private FreightApi freightApi;

    /**
     * 去展示商品
     *
     * @return
     */
    @RequestMapping("toShow")
    public String toShow(ModelMap map) throws Exception {
        List<DictDTO> productList = dictApi.findByParentCode(ProductConstant.PRODUCT_TYPE);
        map.put("productList", productList);
        return "djmall_platform/show";
    }

}