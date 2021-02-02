package com.dj.mall.product.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.common.constant.ProductConstant;
import com.dj.mall.common.util.DozerUtil;

import com.dj.mall.product.api.ProductApi;
import com.dj.mall.product.api.ProductSkuApi;
import com.dj.mall.product.bo.ProductBO;

import com.dj.mall.product.dto.ProductDTO;
import com.dj.mall.product.dto.ProductSkuDTO;
import com.dj.mall.product.entity.ProductEntity;
import com.dj.mall.product.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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
}
