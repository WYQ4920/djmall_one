package com.dj.mall.auth.api.res;

import com.dj.mall.auth.dto.res.ResourceDTO;

import java.util.List;

public interface ZjjResourceApi {

    /**
     * 资源展示
     * @return
     * @throws Exception
     */
    List<ResourceDTO> findResource() throws Exception;

    /**
     * 通过id查资源
     * @param parentId
     * @return
     */
    ResourceDTO getResourceById(Integer parentId) throws Exception;

    /**
     * 新增资源
     * @param resourceDTO
     * @throws Exception
     */
    void addResource(ResourceDTO resourceDTO) throws Exception;

    /**
     * 修改资源
     * @param resourceDTO
     * @throws Exception
     */
    void updateResource(ResourceDTO resourceDTO) throws Exception;

    /**
     * 删除资源
     * @param resourceDTO
     * @throws Exception
     */
    void delResource(ResourceDTO resourceDTO) throws Exception;
}
