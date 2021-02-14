package com.dj.mall.product.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.common.base.QiNiuYun;
import com.dj.mall.common.base.PageResult;
import com.dj.mall.common.base.ResultModel;
import com.dj.mall.common.constant.ProductConstant;
import com.dj.mall.common.util.DozerUtil;

import com.dj.mall.common.util.QiNiuUtil;
import com.dj.mall.product.api.ProductApi;
import com.dj.mall.product.api.ProductSkuApi;
import com.dj.mall.product.bo.ProductBO;

import com.dj.mall.product.dto.ProductDTO;
import com.dj.mall.product.dto.ProductSkuDTO;
import com.dj.mall.product.entity.ProductEntity;
import com.dj.mall.product.mapper.ProductMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author WYQ
 * @Date 2021/2/1 11:23
 */

@Service
public class ProductApiImpl extends ServiceImpl<ProductMapper, ProductEntity> implements ProductApi {

    @Autowired
    private ProductSkuApi productSkuApi;

    /**
     * 获取商品分类
     * @return
     * @throws Exception
     */
    @Override
    public List<ProductDTO> findProductAndDict() throws Exception {
        List<ProductBO> productList = getBaseMapper().findProductAndDict();
        return DozerUtil.mapList(productList, ProductDTO.class);
    }

    /**
     * 新增商品
     * @param productDTO
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addProduct(ProductDTO productDTO, ProductSkuDTO productSkuList, Integer id) throws Exception {
        //将图片保存到七牛
        QiNiuUtil.uploadFile(productDTO.getImg(), productDTO.getProductImg());

        // 新增商品
        productDTO.setProductStatus(ProductConstant.PRODUCT_STATUS);
        productDTO.setIsDel(ProductConstant.PRODUCT_ISDEL);
        ProductEntity productList = DozerUtil.map(productDTO, ProductEntity.class);
        super.save(productList);

        // 新增商品sku
        for (ProductSkuDTO productSku : productSkuList.getSkuList()) {
            productSku.setProductId(productList.getId());
            productSku.setSkuStatus(ProductConstant.SKU_STATUS);
            productSku.setIsDefault(ProductConstant.SKU_ISDEFAULT);
            productSku.setBusinessId(id);
            productSku.setUpdateTime(new Date());
        }
        productSkuApi.addProductSku(productSkuList.getSkuList());

    }

    /**
     * 展示商品
     * @param productDTO
     * @return
     * @throws Exception
     */
    @Override
    public ProductDTO findProductAll(ProductDTO productDTO, Integer pageNo) throws Exception {

        if (null != productDTO.getProductType()) {
            String[] classifyList = productDTO.getProductType().split(",");
            productDTO.setClassifyList(classifyList);
        }

        PageHelper.startPage(pageNo, 3);
        List<ProductBO> pageList = getBaseMapper().findProductAll(DozerUtil.map(productDTO, ProductBO.class));
        PageInfo<ProductBO> pageInfo = new PageInfo<ProductBO>(pageList);

        ProductDTO productDTO1 = new ProductDTO();
        productDTO1.setProductDTOList(DozerUtil.mapList(pageInfo.getList(), ProductDTO.class));
        productDTO1.setPages(pageInfo.getPages());
        return productDTO1;

    }
}
