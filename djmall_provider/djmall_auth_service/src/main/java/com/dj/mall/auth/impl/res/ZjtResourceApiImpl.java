package com.dj.mall.auth.impl.res;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.auth.api.res.ZjtResourceApi;
import com.dj.mall.auth.dto.res.ResourceDTO;
import com.dj.mall.auth.entity.res.ResourceEntity;
import com.dj.mall.auth.mapper.ResourcceMapper;
import com.dj.mall.common.constant.SystemConstant;
import com.dj.mall.common.util.DozerUtil;

import java.util.List;
@Service
public class ZjtResourceApiImpl extends ServiceImpl<ResourcceMapper, ResourceEntity> implements ZjtResourceApi {


    @Override
    public List<ResourceDTO> findResourceAll() {
        return DozerUtil.mapList(super.list(), ResourceDTO.class);
    }

    /**
     * 获取资源id
     * @param parentId
     * @return
     * @throws Exception
     */
    @Override
    public ResourceDTO findResouceBySuperId(Integer parentId) throws Exception {
        return DozerUtil.map(super.getById(parentId),ResourceDTO.class);
    }

    /**
     * 资源新增
     * @param resourceDTO
     * @return
     */
    @Override
    public void addResourceAdd(ResourceDTO resourceDTO) {
        //资源编码大写处理
        resourceDTO.setResourceCode(resourceDTO.getResourceCode().toUpperCase());
        ResourceEntity resourceEntity = DozerUtil.map(resourceDTO, ResourceEntity.class);
        resourceEntity.setIsDel(SystemConstant.ERROR_ONEE);
        this.save(resourceEntity);
    }

    /**
     * 修改资源
     * @param resourceDTO
     */
    @Override
    public void update(ResourceDTO resourceDTO) {
       this.updateById(DozerUtil.map(resourceDTO,ResourceEntity.class));

    }

    /**
     * 删除资源
     * @param resourceDTO
     * @throws Exception
     */
    @Override
    public void del(ResourceDTO resourceDTO) throws Exception {
        this.removeByIds(resourceDTO.getResourceIds());
    }
}
