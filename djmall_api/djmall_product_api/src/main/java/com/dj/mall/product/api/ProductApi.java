package com.dj.mall.product.api;

import com.dj.mall.common.base.PageResult;
import com.dj.mall.common.base.ResultModel;
import com.dj.mall.product.dto.ProductDTO;
import com.dj.mall.product.dto.ProductSkuDTO;

import java.util.List;
import java.util.Map;

/**
 * @Author WYQ
 * @Date 2021/2/1 11:06
 */
public interface ProductApi {

    /**
     * 获取商品分类
     * @return
     * @throws Exception
     */
    List<ProductDTO> findProductAndDict() throws Exception;

    /**
     * 新增商品
     * @param productDTO
     * @throws Exception
     */
    void addProduct(ProductDTO productDTO, ProductSkuDTO productSkuList, Integer id) throws Exception;

    /**
     * 展示商品
     * @param productDTO
     * @param pageNo
     * @return
     * @throws Exception
     */
     ProductDTO findProductAll(ProductDTO productDTO, Integer pageNo) throws Exception;
}
