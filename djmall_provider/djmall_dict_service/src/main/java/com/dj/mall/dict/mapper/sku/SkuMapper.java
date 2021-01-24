package com.dj.mall.dict.mapper.sku;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.mall.dict.bo.sku.SkuBO;
import com.dj.mall.dict.entity.Sku.SkuEntity;

import java.util.List;

/**
 * @Author zhengyk
 * @Date 2021/1/24 21:12
 */
public interface SkuMapper extends BaseMapper<SkuEntity> {

    List<SkuBO> findByProductType(String productType);
}
