package com.dj.mall.auth.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.auth.api.ResourceApi;
import com.dj.mall.auth.dto.ResourceDTO;
import com.dj.mall.auth.entity.ResourceEntity;
import com.dj.mall.auth.mapper.ResourcceMapper;
import com.dj.mall.common.util.DozerUtil;
import java.util.List;

@Service
public class ResourceApiImpl extends ServiceImpl<ResourcceMapper, ResourceEntity> implements ResourceApi {

    @Override
    public List<ResourceDTO> findAll(ResourceDTO resourceDTO) throws Exception {
        List<ResourceEntity> list = this.list();
        return DozerUtil.mapList(list, ResourceDTO.class);
    }

    @Override
    public List<ResourceDTO> findAll1(ResourceDTO resourceDTO) throws Exception {
        QueryWrapper queryWrapper = new QueryWrapper();
        ResourceEntity resourceEntity = DozerUtil.map(resourceDTO, ResourceEntity.class);
        queryWrapper.eq("is_del",resourceEntity.getIsDel());
        List<ResourceEntity> list = this.list(queryWrapper);
        return DozerUtil.mapList(list, ResourceDTO.class);
    }


}
