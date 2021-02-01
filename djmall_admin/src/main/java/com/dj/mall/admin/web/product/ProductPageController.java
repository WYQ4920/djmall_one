package com.dj.mall.admin.web.product;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.common.constant.ProductConstant;
import com.dj.mall.dict.api.DictApi;
import com.dj.mall.dict.api.freight.FreightApi;
import com.dj.mall.dict.dto.DictDTO;
import com.dj.mall.dict.dto.freight.FreightDTO;
import com.dj.mall.product.api.ProductApi;
import com.dj.mall.product.dto.ProductDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    private ProductApi productApi;

    @Reference
    private DictApi dictApi;

    @Reference
    private FreightApi freightApi;

    /**
     * 去展示商品
     * @return
     */
    @RequestMapping("toList")
    public String toList(ModelMap map) throws Exception {
        List<ProductDTO> productList = productApi.findProductAndDict();
        map.put("productList", productList);
        return "/product/product_list";
    }

    /**
     * 去新增商品
     */
    @RequestMapping("toAdd")
    public String toAdd(ModelMap map) throws Exception {
        List<FreightDTO> freightList = freightApi.findFreightAndDict();
        List<DictDTO> productList = dictApi.findByParentCode(ProductConstant.PRODUCT_TYPE);
        map.put("freightList", freightList);
        map.put("productList", productList);
        return "/product/product_add";
    }

    /**
     * 去修改
     */
    @RequestMapping("toUpdate")
    public String toUpdate(Integer id, ModelMap map) {
        map.put("id", id);
        return "/product/product_update";
    }

}
