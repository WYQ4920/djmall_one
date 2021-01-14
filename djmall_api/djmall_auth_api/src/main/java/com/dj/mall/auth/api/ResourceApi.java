package com.dj.mall.auth.api;


import com.dj.mall.auth.dto.ResourceDTO;
import com.dj.mall.common.base.ResultModel;

public interface ResourceApi {
    ResultModel<Object> findAll(ResourceDTO resourceDTO);
}
