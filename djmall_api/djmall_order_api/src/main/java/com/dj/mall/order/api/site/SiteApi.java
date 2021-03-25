package com.dj.mall.order.api.site;


import com.dj.mall.order.dto.area.AreaDTO;
import com.dj.mall.order.dto.site.SiteDTO;

import java.util.List;

/**
 *
 */
public interface SiteApi {

    /**
     * 查询收货地址
     * @return
     */
    List<SiteDTO> findByUserId(Integer id)throws Exception;

    /**
     * 添加收货地址
     */
    void addSite(SiteDTO siteDTO)throws Exception;
}
