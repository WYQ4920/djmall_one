package com.dj.mall.admin.web.dict.attr;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.dict.attr.ProductAttrVOReq;
import com.dj.mall.admin.vo.dict.attr.ProductAttrVOResp;
import com.dj.mall.admin.vo.dict.attr.value.ProductAttrValueVOResp;
import com.dj.mall.common.base.BusinessException;
import com.dj.mall.common.base.ResultModel;
import com.dj.mall.common.util.DozerUtil;
import com.dj.mall.dict.api.attr.ProductAttrApi;
import com.dj.mall.dict.dto.attr.ProductAttrDTO;
import com.dj.mall.dict.dto.attr.ProductAttrValueDTO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author WYQ
 * @Date 2021/1/24 12:00
 */

@RestController
@RequestMapping("/product/attr/")
public class ProductAttrController {

    @Reference
    private ProductAttrApi productAttrApi;

    /**
     * 展示商品属性表
     */
    @GetMapping("list")
    @RequiresPermissions("PRODUCT_ATTRIBUTE_MAINTENANCE_MANAGER")
    public ResultModel list() throws Exception {
        List<ProductAttrDTO> productAttrDTOList = productAttrApi.findProductAttrAll();
        return new ResultModel().success(DozerUtil.mapList(productAttrDTOList, ProductAttrVOResp.class));
    }

    /**
     * 新增商品名
     */
    @PostMapping("add")
    @RequiresPermissions("PRODUCT_ATTRIBUTE_ADD_BTN")
    public ResultModel add(ProductAttrVOReq productAttrVOReq) throws BusinessException {
        Assert.hasText("attr_name", "属性名不能为空");
        productAttrApi.addAttr(DozerUtil.map(productAttrVOReq, ProductAttrDTO.class));
        return new ResultModel().success();
    }

    /**
     * 展示商品属性值表
     */
    @GetMapping("listValue")
    @RequiresPermissions("PRODUCT_RELEVANCE_ATTRIBUTE_VALUE_BTN")
    public ResultModel listValue(Integer id) throws Exception {
        ProductAttrDTO productAttrValueDTOList = productAttrApi.findProductAttrValueAll(id);
        return new ResultModel().success(DozerUtil.map(productAttrValueDTOList, ProductAttrValueVOResp.class));
    }
}
