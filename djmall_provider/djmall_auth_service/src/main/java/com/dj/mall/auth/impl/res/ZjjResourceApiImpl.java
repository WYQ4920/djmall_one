package com.dj.mall.auth.impl.res;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.auth.api.res.ZjjResourceApi;
import com.dj.mall.auth.dto.res.ResourceDTO;
import com.dj.mall.auth.entity.res.ResourceEntity;
import com.dj.mall.auth.mapper.resource.ZjjResourceMapper;
import com.dj.mall.common.util.DozerUtil;

import java.util.List;

/**
 * @author ZJJ
 * @date 2021/1/17 20:07
 */
@Service
public class ZjjResourceApiImpl extends ServiceImpl<ZjjResourceMapper, ResourceEntity> implements ZjjResourceApi {

    /**
     * 资源展示
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<ResourceDTO> findResource() throws Exception {
        return DozerUtil.mapList(super.list(),ResourceDTO.class);
    }

    /**
     * 通过id查资源
     *
     * @param parentId
     * @return
     */
    @Override
    public ResourceDTO getResourceById(Integer parentId) throws Exception {
        return DozerUtil.map(this.getById(parentId),ResourceDTO.class);
    }

    /**
     * 新增资源
     *
     * @param resourceDTO
     * @throws Exception
     */
    @Override
    public void addResource(ResourceDTO resourceDTO) throws Exception {
        this.save(DozerUtil.map(resourceDTO,ResourceEntity.class));
    }

    /**
     * 修改资源
     *
     * @param resourceDTO
     * @throws Exception
     */
    @Override
    public void updateResource(ResourceDTO resourceDTO) throws Exception {
        super.updateById(DozerUtil.map(resourceDTO,ResourceEntity.class));
    }

    /**
     * 删除资源
     *
     * @param resourceDTO
     * @throws Exception
     */
    @Override
    public void delResource(ResourceDTO resourceDTO) throws Exception {
        super.removeByIds(resourceDTO.getResourceIds());
    }
}
