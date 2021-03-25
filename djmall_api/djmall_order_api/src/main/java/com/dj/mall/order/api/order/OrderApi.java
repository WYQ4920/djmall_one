package com.dj.mall.order.api.order;

import com.dj.mall.common.base.PageResult;

/**
 * @Author zhengyk
 * @Date 2021/2/5 17:11
 */
public interface OrderApi {

    /**
     * 待支付,已取消
     * @param orderStatus
     * @param pageNo
     * @param buyerId
     * @return
     */
    PageResult findOrderByStatus(String orderStatus, Integer pageNo, Integer buyerId);
}
