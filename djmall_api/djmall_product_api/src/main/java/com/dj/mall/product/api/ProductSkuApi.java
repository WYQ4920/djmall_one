package com.dj.mall.product.api;

import com.dj.mall.product.dto.ProductSkuDTO;

import java.util.List;

/**
 * @Author WYQ
 * @Date 2021/2/1 11:06
 */
public interface ProductSkuApi {

    /**
     * 新增sku
     * @param productSkuList
     * @throws Exception
     */
    void addProductSku(List<ProductSkuDTO> productSkuList) throws Exception;
}
