package com.dj.mall.auth.api.res;

import com.dj.mall.auth.dto.res.ResourceDTO;

import java.util.List;

/**
 * @Author WYQ
 * @Date 2021/1/15 22:13
 * 资源表 -> api
 */
public interface WyqResourceApi {

    /**
     * 展示资源
     * @return
     * @throws Exception
     */
    List<ResourceDTO> findResourceAllByName() throws Exception;

    /**
     * 获取对应角色资源
     * @param id 资源id
     * @return
     * @throws Exception
     */
    ResourceDTO getResource(Integer id) throws Exception;

    /**
     * 新增资源
     * @param resourceDTO 资源信息
     * @return
     * @throws Exception
     */
    boolean addResource(ResourceDTO resourceDTO) throws Exception;

    /**
     * 修改资源
     * @param resourceDTO 资源信息
     * @return
     * @throws Exception
     */
    boolean updateResource(ResourceDTO resourceDTO) throws Exception;

    /**
     * 删除资源
     * @param resourceDTO 资源信息
     * @throws Exception
     */
    boolean delResource(ResourceDTO resourceDTO) throws Exception;

}
