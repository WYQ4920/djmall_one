package com.dj.mall.platform.web.order;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.common.base.PageResult;
import com.dj.mall.common.base.ResultModel;
import com.dj.mall.common.constant.CacheConstant;
import com.dj.mall.common.util.DozerUtil;
import com.dj.mall.order.api.order.OrderApi;
import com.dj.mall.platform.vo.order.OrderVOResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author zhengyk
 * @Date 2021/2/5 17:08
 */
@RequestMapping("/order/")
@RestController
public class OrderController {

    @Reference
    private OrderApi orderApi;

    @Autowired
    RedisTemplate redisTemplate;

    @PostMapping("show")
    public ResultModel show(HttpServletRequest httpServletRequest, String orderStatus, Integer pageNo){

        // redis中获取登陆人信息
        // String token = httpServletRequest.getHeader("TOKEN");
        // UserDTO userDTO = (UserDTO) redisTemplate.opsForValue().get(CacheConstant.USER_TOKEN + token);
        Integer buyerId = 13;
        PageResult ipage = orderApi.findOrderByStatus(orderStatus, pageNo, buyerId);
        return new ResultModel().success(PageResult.pageInfo(ipage.getCurrent(),ipage.getPages(), DozerUtil.mapList(ipage.getRecords(), OrderVOResp.class)));
    }

}
