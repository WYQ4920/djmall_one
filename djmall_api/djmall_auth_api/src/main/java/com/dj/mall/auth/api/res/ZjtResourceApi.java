package com.dj.mall.auth.api.res;

import com.dj.mall.auth.dto.res.ResourceDTO;

import java.util.List;

public interface ZjtResourceApi {
    /**
     * 展示
     * @return
     */
    List<ResourceDTO> findResourceAll() throws Exception;

    /**
     * 父级id查询
     * @param parentId
     * @return
     * @throws Exception
     */
    ResourceDTO findResouceBySuperId(Integer parentId) throws  Exception;

    /**
     * 添加资源
     * @param resourceDTO
     * @throws Exception
     */
    void addResourceAdd(ResourceDTO resourceDTO)throws  Exception;;

    /**
     * 修改资源
     * @param resourceDTO
     * @throws Exception
     */
    void update(ResourceDTO resourceDTO)throws  Exception;;

    /**
     * 删除资源
     * @param resourceDTO
     * @throws Exception
     */
    void del(ResourceDTO resourceDTO)throws  Exception;;
}
