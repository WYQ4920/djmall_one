package com.dj.mall.dict.api.attr;

import com.dj.mall.common.base.BusinessException;
import com.dj.mall.dict.dto.attr.ProductAttrDTO;
import com.dj.mall.dict.dto.attr.ProductAttrValueDTO;

import java.util.List;

/**
 * @Author WYQ
 * @Date 2021/1/24 12:15
 */
public interface ProductAttrApi {

    /**
     * 展示商品属性表
     * @return
     */
    List<ProductAttrDTO> findProductAttrAll() throws Exception;

    /**
     * 新增属性名
     * @param productAttrDTO
     * @throws BusinessException
     */
    void addAttr(ProductAttrDTO productAttrDTO) throws BusinessException;

    /**
     * 根据商品属性id查
     * @param id
     * @return
     * @throws Exception
     */
    ProductAttrDTO findAttrById(Integer id) throws Exception;

}
