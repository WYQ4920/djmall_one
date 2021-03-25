package com.dj.mall.platform.web.order;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.common.constant.CacheConstant;
import com.dj.mall.order.api.site.SiteApi;
import com.dj.mall.order.dto.site.SiteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author zhengyk
 * @Date 2021/2/5 17:07
 */
@RequestMapping("/order/")
@Controller
public class OrderPageController {

    @Reference
    private SiteApi siteApi;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 确认订单
     * @param httpServletRequest
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("toDetermine")
    public String toDetermine(HttpServletRequest httpServletRequest,Model model) throws Exception {
//        String token = httpServletRequest.getHeader("TOKEN");
//        UserDTO userDTO = (UserDTO) redisTemplate.opsForValue().get(CacheConstant.USER_TOKEN + token);

        List<SiteDTO> siteList = siteApi.findByUserId(13);
        model.addAttribute("siteList",siteList);
        return "order/determine_order";
    }

    /**
     * 我的订单，待支付
     * @param httpServletRequest
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("toOrder")
    public String toOrder(HttpServletRequest httpServletRequest,Model model) throws Exception {
        return "order/order";
    }

}
