package com.dj.mall.order.impl.site;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.common.util.DozerUtil;
import com.dj.mall.order.api.area.AreaApi;
import com.dj.mall.order.api.site.SiteApi;
import com.dj.mall.order.dto.area.AreaDTO;
import com.dj.mall.order.dto.site.SiteDTO;
import com.dj.mall.order.entity.site.Site;
import com.dj.mall.order.mapper.site.SiteMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author zhengyk
 * @Date 2021/1/4 14:00
 */
@Service
public class SiteApiImpl extends ServiceImpl<SiteMapper, Site> implements SiteApi {

    @Autowired
    private AreaApi areaApi;

    /**
     * 查询用户地址
     *
     * @return
     */
    @Override
    public List<SiteDTO> findByUserId(Integer id) throws Exception {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",id);
        List<Site> siteList1 = super.list(queryWrapper);
        List<SiteDTO> siteList = DozerUtil.mapList(siteList1, SiteDTO.class);

        List<AreaDTO> areaList = areaApi.findAll();
        for (AreaDTO areaDTO : areaList) {
            for (SiteDTO siteDTO : siteList) {
                String s = areaDTO.getId().toString();
                if(siteDTO.getSheng().equals(s)){
                    siteDTO.setSheng(areaDTO.getAreaName());
                }

                if(siteDTO.getShi().equals(s)){
                    siteDTO.setShi(areaDTO.getAreaName());
                }

                if(siteDTO.getQu().equals(s)){
                    siteDTO.setQu(areaDTO.getAreaName());
                }
            }
        }
        return siteList;
    }

    /**
     * 添加收货地址
     *
     * @param siteDTO
     */
    @Override
    public void addSite(SiteDTO siteDTO) throws Exception {
        super.save(DozerUtil.map(siteDTO,Site.class));
    }
}
