package com.dj.mall.order.api.area;


import com.dj.mall.order.dto.area.AreaDTO;

import java.util.List;

/**
 * @Author zhengyk
 * @Date 2021/1/4 13:59
 */
public interface AreaApi {
    List<AreaDTO> findAll() throws Exception;

    List<AreaDTO> findByParentId(Integer parentId);
}
