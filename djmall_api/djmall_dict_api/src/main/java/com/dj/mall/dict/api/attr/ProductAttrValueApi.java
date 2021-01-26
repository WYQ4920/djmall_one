package com.dj.mall.dict.api.attr;

import com.dj.mall.common.base.BusinessException;
import com.dj.mall.dict.dto.attr.ProductAttrValueDTO;

import java.util.List;

/**
 * @Author WYQ
 * @Date 2021/1/26 19:57
 */
public interface ProductAttrValueApi {

    /**
     * 展示商品属性值表
     * @param attrId
     * @return
     * @throws Exception
     */
    List<ProductAttrValueDTO> findProductAttrValueAll(Integer attrId) throws Exception;

    /**
     * 新增属性值
     * @param productAttrValueDTO
     * @throws BusinessException
     */
    void addAttrValue(ProductAttrValueDTO productAttrValueDTO) throws BusinessException;

    /**
     * 移除属性值
     * @param id
     * @throws BusinessException
     */
    void removeAttrValue(Integer id) throws BusinessException;
}
