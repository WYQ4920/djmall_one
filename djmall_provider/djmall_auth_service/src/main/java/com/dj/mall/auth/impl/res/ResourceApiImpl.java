package com.dj.mall.auth.impl.res;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.auth.api.res.ResourceApi;
import com.dj.mall.auth.dto.res.ResourceDTO;
import com.dj.mall.auth.entity.res.ResourceEntity;
import com.dj.mall.auth.mapper.ResourcceMapper;
import com.dj.mall.common.base.BusinessException;
import com.dj.mall.common.util.DozerUtil;
import java.util.List;

@Service
public class ResourceApiImpl extends ServiceImpl<ResourcceMapper, ResourceEntity> implements ResourceApi {

    /**
     * left
     * @param resourceDTO
     * @return
     * @throws Exception
     */
    @Override
    public List<ResourceDTO> findAll() throws Exception {
        List<ResourceEntity> list = this.list();
        return DozerUtil.mapList(list, ResourceDTO.class);
    }

    /**
     * res展示
     * @param resourceDTO
     * @return
     * @throws Exception
     */
    @Override
    public List<ResourceDTO> findAll1(ResourceDTO resourceDTO) throws Exception {
        QueryWrapper queryWrapper = new QueryWrapper();
        ResourceEntity resourceEntity = DozerUtil.map(resourceDTO, ResourceEntity.class);
        queryWrapper.eq("is_del",resourceEntity.getIsDel());
        List<ResourceEntity> list = super.list(queryWrapper);
        return DozerUtil.mapList(list, ResourceDTO.class);
    }

    /**
     * 查重
     * @param resourceName
     * @return
     * @throws Exception
     */
    @Override
    public Boolean findByResourceName(String resourceName) throws BusinessException {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("resource_name",resourceName);
        ResourceEntity one = super.getOne(queryWrapper);
        return one == null?true:false;
    }

    /**
     * 新增
     * @param resourceDTO
     * @throws BusinessException
     */
    @Override
    public void addRes(ResourceDTO resourceDTO) throws BusinessException {
        ResourceEntity resourceEntity = DozerUtil.map(resourceDTO, ResourceEntity.class);
        Boolean byResourceName = this.findByResourceName(resourceEntity.getResourceName());
        if(byResourceName){
            super.save(resourceEntity);
            return;
        }
        throw new BusinessException("重名");
    }

    /**
     * 修改
     * @param resourceDTO
     * @throws BusinessException
     */
    @Override
    public void updateRes(ResourceDTO resourceDTO) throws BusinessException {
        ResourceEntity resourceEntity = DozerUtil.map(resourceDTO, ResourceEntity.class);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("resource_name",resourceEntity.getResourceName());
        ResourceEntity one = this.getOne(queryWrapper);
        if(one != null && !resourceEntity.getId().equals(one.getId())){
           throw new BusinessException("重名");
        }
        super.updateById(resourceEntity);
    }

    /**
     * del
     * @param resourceIds
     */
    @Override
    public void delRes(List<Integer> resourceIds) {
        super.removeByIds(resourceIds);
    }


}
