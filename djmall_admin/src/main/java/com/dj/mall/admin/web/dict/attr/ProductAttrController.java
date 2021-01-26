package com.dj.mall.admin.web.dict.attr;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.dict.attr.ProductAttrVOReq;
import com.dj.mall.admin.vo.dict.attr.ProductAttrVOResp;
import com.dj.mall.admin.vo.dict.attr.value.ProductAttrValueVOReq;
import com.dj.mall.admin.vo.dict.attr.value.ProductAttrValueVOResp;
import com.dj.mall.common.base.BusinessException;
import com.dj.mall.common.base.ResultModel;
import com.dj.mall.common.util.DozerUtil;
import com.dj.mall.dict.api.attr.ProductAttrApi;
import com.dj.mall.dict.api.attr.ProductAttrValueApi;
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

    @Reference
    private ProductAttrValueApi productAttrValueApi;

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
    @PostMapping("listValue")
    public ResultModel listValue(Integer attrId) throws Exception {
        List<ProductAttrValueDTO> productAttrValueDTOList = productAttrValueApi.findProductAttrValueAll(attrId);
        return new ResultModel().success(DozerUtil.mapList(productAttrValueDTOList, ProductAttrValueVOResp.class));
    }

    /**
     * 新增属性值
     */
    @PostMapping("addAttrValue")
    @RequiresPermissions("RELEVANCE_ATTRIBUTE_VALUE_ADD_BTN")
    public ResultModel addAttrValue(ProductAttrValueVOReq productAttrValueVOReq) throws BusinessException {
        Assert.hasText("attr_value", "属性值不能为空");
        productAttrValueApi.addAttrValue(DozerUtil.map(productAttrValueVOReq, ProductAttrValueDTO.class));
        return new ResultModel().success();
    }

    /**
     * 删除属性值
     */
    @PostMapping("removeAttrValue")
    @RequiresPermissions("RELEVANCE_ATTRIBUTE_VALUE_REMOVE_BTN")
    public ResultModel removeAttrValue(Integer id) throws Exception {
        productAttrValueApi.removeAttrValue(id);
        return new ResultModel().success();
    }
}
