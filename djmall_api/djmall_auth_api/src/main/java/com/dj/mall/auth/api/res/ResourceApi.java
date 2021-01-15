package com.dj.mall.auth.api.res;


import com.dj.mall.auth.dto.res.ResourceDTO;
import com.dj.mall.common.base.BusinessException;

import java.util.List;

public interface ResourceApi {
    List<ResourceDTO> findAll(ResourceDTO resourceDTO) throws Exception;

    List<ResourceDTO> findAll1(ResourceDTO resourceDTO) throws Exception;

    void addRes(ResourceDTO resourceDTO) throws Exception;

    Boolean findByResourceName(String resourceName) throws Exception;

    void updeteRes(ResourceDTO resourceDTO)throws BusinessException;

    ResourceDTO findResById(Integer id) throws Exception;
}
