package com.dj.mall.auth.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.auth.api.ResourceApi;
import com.dj.mall.auth.entity.ResourceEntity;
import com.dj.mall.auth.mapper.ResourcceMapper;

@Service
public class ResourceApiImpl extends ServiceImpl<ResourcceMapper, ResourceEntity> implements ResourceApi {
}
