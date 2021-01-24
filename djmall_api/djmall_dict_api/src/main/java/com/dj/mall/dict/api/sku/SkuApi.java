package com.dj.mall.dict.api.sku;

import com.dj.mall.dict.dto.DictDTO;
import com.dj.mall.dict.dto.sku.SkuDTO;

import java.util.List;

/**
 * @Author zhengyk
 * @Date 2021/1/24 21:09
 */
public interface SkuApi {

    /**
     * sku展示
     * @param productType
     * @return
     */
    List<SkuDTO> findByProductType(String productType);


    /**
     * code 查
     * @param code
     * @return
     */
    List<SkuDTO> findByCode(String code);


    /**
     * 关联
     * @param skuDTO
     */
    void updateSku(SkuDTO skuDTO);
}
