package com.dj.mall.product.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.product.api.ProductSkuApi;
import com.dj.mall.product.entity.ProductSkuEntity;
import com.dj.mall.product.mapper.ProductSkuMapper;

/**
 * @Author WYQ
 * @Date 2021/2/1 11:24
 */

@Service
public class ProductSkuApiImpl extends ServiceImpl<ProductSkuMapper, ProductSkuEntity> implements ProductSkuApi {
}
