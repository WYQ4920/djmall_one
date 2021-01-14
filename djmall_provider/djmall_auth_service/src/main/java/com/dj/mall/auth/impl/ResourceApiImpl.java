package com.dj.mall.auth.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.auth.api.ResourceApi;
import com.dj.mall.auth.dto.ResourceDTO;
import com.dj.mall.auth.dto.UserDTO;
import com.dj.mall.auth.entity.ResourceEntity;
import com.dj.mall.auth.entity.UserEntity;
import com.dj.mall.auth.mapper.ResourcceMapper;
import com.dj.mall.common.base.ResultModel;
import com.dj.mall.common.util.DozerUtil;

import java.util.List;

@Service
public class ResourceApiImpl extends ServiceImpl<ResourcceMapper, ResourceEntity> implements ResourceApi {

    @Override
    public List<ResourceDTO> findAll(ResourceDTO resourceDTO) {
        List<ResourceEntity> list = this.list();
        return DozerUtil.mapList(list, ResourceDTO.class);
    }
}
