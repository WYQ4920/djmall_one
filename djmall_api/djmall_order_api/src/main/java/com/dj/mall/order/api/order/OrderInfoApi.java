package com.dj.mall.order.api.order;

import com.dj.mall.common.base.PageResult;

/**
 * @Author zhengyk
 * @Date 2021/2/5 17:11
 */
public interface OrderInfoApi {
    /**
     * 子集订单展示
     * @param orderStatus
     * @param pageNo
     * @param buyerId
     * @return
     */
    PageResult findOrderByStatus(String orderStatus, Integer pageNo, Integer buyerId);
}
