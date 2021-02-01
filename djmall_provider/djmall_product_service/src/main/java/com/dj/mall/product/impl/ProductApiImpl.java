package com.dj.mall.product.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.product.api.ProductApi;
import com.dj.mall.product.entity.ProductEntity;
import com.dj.mall.product.mapper.ProductMapper;

/**
 * @Author WYQ
 * @Date 2021/2/1 11:23
 */

@Service
public class ProductApiImpl extends ServiceImpl<ProductMapper, ProductEntity> implements ProductApi {
}
