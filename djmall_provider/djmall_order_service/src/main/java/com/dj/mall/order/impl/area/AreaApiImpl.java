package com.dj.mall.order.impl.area;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.common.util.DozerUtil;
import com.dj.mall.order.api.area.AreaApi;
import com.dj.mall.order.dto.area.AreaDTO;
import com.dj.mall.order.entity.area.Area;
import com.dj.mall.order.mapper.area.AreaMapper;


import java.util.List;

/**
 * @Author zhengyk
 * @Date 2021/1/4 14:00
 */
@Service
public class AreaApiImpl extends ServiceImpl<AreaMapper, Area> implements AreaApi {
    @Override
    public List<AreaDTO> findAll() {

        List<Area> list = super.list();

        return DozerUtil.mapList(list,AreaDTO.class);

    }

    @Override
    public List<AreaDTO> findByParentId(Integer parentId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("parent_id",parentId);
        List list = super.baseMapper.selectList(queryWrapper);
        return DozerUtil.mapList(list,AreaDTO.class);
    }
}
