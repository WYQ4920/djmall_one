package com.dj.mall.admin.web.product;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.dict.attr.ProductAttrVOResp;
import com.dj.mall.common.base.ResultModel;
import com.dj.mall.common.util.DozerUtil;
import com.dj.mall.dict.api.DictApi;
import com.dj.mall.dict.dto.attr.ProductAttrDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author WYQ
 * @Date 2021/1/27 16:46
 */

@RestController
@RequestMapping("/product/")
public class ProductController {

    @Reference
    private DictApi dictApi;

    /**
     * 展示商品属性
     * @param productType
     * @return
     */
    @GetMapping("list")
    public ResultModel list(String productType) throws Exception {
        List<ProductAttrDTO> productAttrList = dictApi.findAttrAndSku(productType);
        return new ResultModel().success(DozerUtil.mapList(productAttrList, ProductAttrVOResp.class));
    }

}
