package com.dj.mall.order.impl.order;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.common.base.PageResult;
import com.dj.mall.common.util.DozerUtil;
import com.dj.mall.order.api.order.OrderApi;
import com.dj.mall.order.bo.OrderBO;
import com.dj.mall.order.dto.order.OrderDTO;
import com.dj.mall.order.entity.order.OrderEntity;
import com.dj.mall.order.mapper.order.OrderMapper;

/**
 * @Author zhengyk
 * @Date 2021/2/5 17:14
 */
@Service
public class OrderApiImpl extends ServiceImpl<OrderMapper, OrderEntity> implements OrderApi {
    /**
     * 待支付,已取消
     *
     * @param orderStatus
     * @param buyerId
     * @return
     */
    @Override
    public PageResult findOrderByStatus(String orderStatus, Integer pageNo, Integer buyerId) {

        IPage<OrderBO> ipage = super.baseMapper.findOrderByStatus(new Page<>(pageNo, 3), orderStatus, buyerId);

        return PageResult.pageInfo(ipage.getCurrent(),ipage.getPages(), DozerUtil.mapList(ipage.getRecords(), OrderDTO.class));

    }
}
