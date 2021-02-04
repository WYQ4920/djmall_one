package com.dj.mall.admin.web.product;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.dict.attr.ProductAttrVOResp;
import com.dj.mall.admin.vo.product.ProductSkuVOReq;
import com.dj.mall.admin.vo.product.ProductVOReq;
import com.dj.mall.admin.vo.product.ProductVOResp;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.common.base.QiNiuYun;
import com.dj.mall.common.base.PageResult;
import com.dj.mall.common.base.ResultModel;
import com.dj.mall.common.constant.UserConstant;
import com.dj.mall.common.util.DozerUtil;
import com.dj.mall.common.util.QiNiuUtil;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    public ResultModel listClassify(String productType) throws Exception {
        List<ProductAttrDTO> productAttrList = dictApi.findAttrAndSku(productType);
        return new ResultModel().success(DozerUtil.mapList(productAttrList, ProductAttrVOResp.class));
    }

    /**
     * 新增商品
     */
    @PostMapping("addProduct")
    //MultipartFile是SpringMVC提供简化上传操作的工具类
    public ResultModel addProduct(ProductVOReq productVOReq, ProductSkuVOReq productSkuList, HttpSession session, MultipartFile img) throws Exception {
        Assert.notNull(img, "请上传图片");
        //生成新的图片名
        String lName = UUID.randomUUID().toString().replace("-", "");
        String rName = img.getOriginalFilename().substring(img.getOriginalFilename().lastIndexOf("."));
        productVOReq.setProductImg(lName+rName);

        Assert.hasText(productVOReq.getProductName(), "商品名称不能为空");
        Assert.hasText(productVOReq.getProductType(), "商品类型不能为空");
        Assert.hasText(productVOReq.getProductPostage(), "商品邮费不能为空");
        Assert.hasText(productVOReq.getProductDes(), "商品描述不能为空");
        // 获取商户id
        UserDTO user = (UserDTO) session.getAttribute(UserConstant.USER_SESSION);

        ProductDTO productDTO = DozerUtil.map(productVOReq, ProductDTO.class);
        productDTO.setImg(img.getBytes());

        productApi.addProduct(productDTO, DozerUtil.map(productSkuList, ProductSkuDTO.class), user.getRoleId());
        return new ResultModel().success();
    }


    /**
     * 展示商品
     * @param productVOReq
     * @return
     */
    @GetMapping("list")
    public ResultModel list(ProductVOReq productVOReq, Integer pageNo) throws Exception {

        ProductDTO list = productApi.findProductAll(DozerUtil.map(productVOReq, ProductDTO.class), pageNo);
        return new ResultModel().success(list);

    }

    @GetMapping("delete")
    public ResultModel<Object> delete(String key){
        QiNiuUtil.delFile(key);
        return new ResultModel<>().success();
    }

}
