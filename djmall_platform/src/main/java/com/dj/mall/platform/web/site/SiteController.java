package com.dj.mall.platform.web.site;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.common.base.ResultModel;
import com.dj.mall.common.constant.CacheConstant;
import com.dj.mall.common.util.DozerUtil;
import com.dj.mall.order.api.area.AreaApi;
import com.dj.mall.order.api.site.SiteApi;
import com.dj.mall.order.dto.area.AreaDTO;
import com.dj.mall.order.dto.site.SiteDTO;
import com.dj.mall.platform.vo.area.AreaVOResp;
import com.dj.mall.platform.vo.site.SiteVOReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author zhengyk
 * @Date 2021/1/4 13:59
 */
@RestController
@RequestMapping("/site/")
public class SiteController {

    @Reference
    private SiteApi siteApi;

    @Autowired
    RedisTemplate redisTemplate;


    @GetMapping("list")
    public ResultModel list (HttpServletRequest httpServletRequest) throws Exception {

        String token = httpServletRequest.getHeader("TOKEN");
        UserDTO userDTO = (UserDTO) redisTemplate.opsForValue().get(CacheConstant.USER_TOKEN + token);

        List<SiteDTO> siteList = siteApi.findByUserId(13);
        return new ResultModel().success(siteList);
    }

    @RequestMapping("add")
    public ResultModel add (HttpServletRequest httpServletRequest,SiteVOReq siteVOReq) throws Exception {
         //String token = httpServletRequest.getHeader("TOKEN");
         //UserDTO userDTO = (UserDTO) redisTemplate.opsForValue().get(CacheConstant.USER_TOKEN + token);
        siteVOReq.setUserId(13);
        siteApi.addSite(DozerUtil.map(siteVOReq, SiteDTO.class));
        return new ResultModel().success();
    }


}
