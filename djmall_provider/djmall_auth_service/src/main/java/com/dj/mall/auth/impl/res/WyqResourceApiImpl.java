package com.dj.mall.auth.impl.res;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.auth.api.res.WyqResourceApi;
import com.dj.mall.auth.dto.res.ResourceDTO;
import com.dj.mall.auth.entity.res.ResourceEntity;
import com.dj.mall.auth.mapper.ResourcceMapper;
import com.dj.mall.common.util.DozerUtil;

import java.util.List;

/**
 * @Author WYQ
 * @Date 2021/1/15 22:14
 * 资源表 -> api实现类
 */

@Service
public class WyqResourceApiImpl extends ServiceImpl<ResourcceMapper, ResourceEntity> implements WyqResourceApi {

    /**
     * 展示资源
     * @return
     * @throws Exception
     */
    @Override
    public List<ResourceDTO> findResourceAllByName() throws Exception {
        return DozerUtil.mapList(super.list(), ResourceDTO.class);
    }

    /**
     * 获取对应角色资源
     * @param id 资源id
     * @return
     * @throws Exception
     */
    @Override
    public ResourceDTO getResource(Integer id) throws Exception {
        return DozerUtil.map(super.getById(id), ResourceDTO.class);
    }

    /**
     * 新增资源
     * @param resourceDTO 资源信息
     * @return
     * @throws Exception
     */
    @Override
    public boolean addResource(ResourceDTO resourceDTO) throws Exception {
        resourceDTO.setResourceCode(resourceDTO.getResourceCode().toUpperCase());
        return this.save(DozerUtil.map(resourceDTO, ResourceEntity.class));
    }

    /**
     * 修改资源
     * @param resourceDTO 资源信息
     * @return
     * @throws Exception
     */
    @Override
    public boolean updateResource(ResourceDTO resourceDTO) throws Exception {
        super.updateById(DozerUtil.map(resourceDTO, ResourceEntity.class));
        return true;
    }

    /**
     * 删除资源
     * @param resourceDTO 资源信息
     * @throws Exception
     */
    @Override
    public boolean delResource(ResourceDTO resourceDTO) throws Exception {
        super.removeByIds(resourceDTO.getResourceIds());
        return true;
    }

}
