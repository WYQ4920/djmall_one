package com.dj.mall.auth.api;


import com.dj.mall.auth.dto.ResourceDTO;

import java.util.List;

public interface ResourceApi {
    List<ResourceDTO> findAll(ResourceDTO resourceDTO) throws Exception;

    List<ResourceDTO> findAll1(ResourceDTO resourceDTO) throws Exception;
}
