package com.dj.mall.auth.impl.resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.auth.api.res.WyqResourceApi;
import com.dj.mall.auth.dto.res.ResourceDTO;
import com.dj.mall.auth.entity.resource.ResourceEntity;
import com.dj.mall.auth.entity.role.RoleResourceEntity;
import com.dj.mall.auth.mapper.resource.ResourcceMapper;
import com.dj.mall.auth.service.role.RoleResourceService;
import com.dj.mall.common.base.BusinessException;
import com.dj.mall.common.util.DozerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author WYQ
 * @Date 2021/1/15 22:14
 * 资源表 -> api实现类
 */

@Service
public class WyqResourceApiImpl extends ServiceImpl<ResourcceMapper, ResourceEntity> implements WyqResourceApi {

    @Autowired
    private RoleResourceService roleResourceService;

    /**
     * 展示资源
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<ResourceDTO> findResourceAllByName() throws Exception {
        return DozerUtil.mapList(super.list(), ResourceDTO.class);
    }

    /**
     * 获取对应角色资源
     *
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
     *
     * @param resourceDTO 资源信息
     * @return
     * @throws Exception
     */
    @Override
    public void addResource(ResourceDTO resourceDTO) throws BusinessException {
        resourceDTO.setResourceCode(resourceDTO.getResourceCode().toUpperCase());
        this.save(DozerUtil.map(resourceDTO, ResourceEntity.class));
    }

    /**
     * 修改资源
     *
     * @param resourceDTO 资源信息
     * @return
     * @throws Exception
     */
    @Override
    public void updateResource(ResourceDTO resourceDTO) throws BusinessException {
        super.updateById(DozerUtil.map(resourceDTO, ResourceEntity.class));
    }

    /*    */

    /**
     * 删除资源
     *
     * @param resourceDTO 资源信息
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delResource(ResourceDTO resourceDTO) throws Exception {
        // 删除资源表
        super.removeByIds(resourceDTO.getResourceIds());
        // 删除角色资源表
        QueryWrapper<RoleResourceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("resource_id", resourceDTO.getResourceIds());
        roleResourceService.remove(queryWrapper);
    }

}
