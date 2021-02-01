package com.dj.mall.admin.web.product;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.dict.attr.ProductAttrVOResp;
import com.dj.mall.admin.vo.product.ProductSkuVOReq;
import com.dj.mall.admin.vo.product.ProductVOReq;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.common.base.ResultModel;
import com.dj.mall.common.constant.UserConstant;
import com.dj.mall.common.util.DozerUtil;
import com.dj.mall.dict.api.DictApi;
import com.dj.mall.dict.dto.attr.ProductAttrDTO;
import com.dj.mall.product.api.ProductApi;
import com.dj.mall.product.dto.ProductDTO;
import com.dj.mall.product.dto.ProductSkuDTO;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
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

    @Reference
    private ProductApi productApi;

    /**
     * 展示商品属性及属性值
     * @param productType
     * @return
     */
    @GetMapping("listClassify")
    public ResultModel list(String productType) throws Exception {
        List<ProductAttrDTO> productAttrList = dictApi.findAttrAndSku(productType);
        return new ResultModel().success(DozerUtil.mapList(productAttrList, ProductAttrVOResp.class));
    }

    /**
     * 新增商品
     */
    @PostMapping("addProduct")
    public ResultModel addProduct(ProductVOReq productVOReq, ProductSkuVOReq productSkuList, HttpSession session) throws Exception {
        Assert.hasText(productVOReq.getProductName(), "商品名称不能为空");
        Assert.hasText(productVOReq.getProductType(), "商品类型不能为空");
        Assert.hasText(productVOReq.getProductPostage(), "商品邮费不能为空");
        Assert.hasText(productVOReq.getProductDes(), "商品描述不能为空");
        // 获取商户id
        UserDTO user = (UserDTO) session.getAttribute(UserConstant.USER_SESSION);
        productApi.addProduct(DozerUtil.map(productVOReq, ProductDTO.class), DozerUtil.map(productSkuList, ProductSkuDTO.class), user.getRoleId());
        return new ResultModel().success();
    }

}
