package com.dj.mall.auth.api;


import com.dj.mall.auth.dto.ResourceDTO;

import java.util.List;

public interface ResourceApi {
    List<ResourceDTO> findAll(ResourceDTO resourceDTO) throws Exception;

    List<ResourceDTO> findAll1(ResourceDTO resourceDTO) throws Exception;

    void addRes(ResourceDTO resourceDTO) throws Exception;

    Boolean findByResourceName(String resourceName) throws Exception;

    void updeteRes(ResourceDTO resourceDTO)throws Exception;

    ResourceDTO findResById(Integer id) throws Exception;
}
