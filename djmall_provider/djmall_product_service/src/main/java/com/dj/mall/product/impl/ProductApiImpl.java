package com.dj.mall.product.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.common.base.QiNiuYun;
import com.dj.mall.common.base.PageResult;
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
        //将图片保存到七牛
        QiNiuYun.uploadFile(productDTO.getImg(), productDTO.getProductImg());

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
    public PageResult findProductAll(ProductDTO productDTO, Integer pageNo) throws Exception {
        /*QueryWrapper<ProductEntity> queryWrapper = new QueryWrapper<>();
        if (null != productDTO.getProductName()) {
            queryWrapper.like("product_name", productDTO.getProductName());
        }
        if (null != productDTO.getProductType()) {
            String[] ProductTypes = productDTO.getProductType().split(",");
            for (String pt : ProductTypes) {
                queryWrapper.eq("product_type", productDTO.getProductType()).or();
            }
        }
        return DozerUtil.mapList(super.list(queryWrapper), ProductDTO.class);*/

        if (null != productDTO.getProductName()) {
            String[] classifyList = productDTO.getProductType().split(",");
            productDTO.setClassifyList(classifyList);
        }
        IPage<ProductBO> pageList = getBaseMapper().findProductAll(new Page<ProductBO>(pageNo, ProductConstant.PAGE_SIZE), DozerUtil.map(productDTO, ProductBO.class));
        return PageResult.pageInfo(
                pageList.getCurrent(),
                pageList.getPages(),
                DozerUtil.mapList(pageList.getRecords(), ProductDTO.class));
    }
}
