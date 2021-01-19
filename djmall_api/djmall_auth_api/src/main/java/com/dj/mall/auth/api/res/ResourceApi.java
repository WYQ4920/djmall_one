package com.dj.mall.auth.api.res;


import com.dj.mall.auth.dto.res.ResourceDTO;
import com.dj.mall.common.base.BusinessException;
import com.google.common.reflect.ClassPath;

import java.util.List;

public interface ResourceApi {
    List<ResourceDTO> findAll() throws Exception;

    List<ResourceDTO> findAll1(ResourceDTO resourceDTO) throws Exception;

    void addRes(ResourceDTO resourceDTO) throws Exception;

    Boolean findByResourceName(String resourceName) throws Exception;

    void updateRes(ResourceDTO resourceDTO) throws BusinessException;

    void delRes(List<Integer> resourceIds);


}
