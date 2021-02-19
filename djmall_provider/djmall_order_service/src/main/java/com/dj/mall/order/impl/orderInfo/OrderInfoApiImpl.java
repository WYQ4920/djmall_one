package com.dj.mall.order.impl.orderInfo;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.common.base.PageResult;
import com.dj.mall.common.util.DozerUtil;
import com.dj.mall.order.api.order.OrderInfoApi;
import com.dj.mall.order.bo.OrderBO;
import com.dj.mall.order.bo.OrderInfoBO;
import com.dj.mall.order.dto.order.OrderDTO;
import com.dj.mall.order.dto.orderInfo.OrderInfoDTO;
import com.dj.mall.order.entity.orderInfo.OrderInfoEntity;
import com.dj.mall.order.mapper.orderInfo.OrderInfoMapper;

/**
 * @Author zhengyk
 * @Date 2021/2/5 17:14
 */
@Service
public class OrderInfoApiImpl extends ServiceImpl<OrderInfoMapper, OrderInfoEntity> implements OrderInfoApi {
    /**
     * 子集订单展示
     *
     * @param orderStatus
     * @param pageNo
     * @param buyerId
     * @return
     */
    @Override
    public PageResult findOrderByStatus(String orderStatus, Integer pageNo, Integer buyerId) {

        String[] orderStatusArr = orderStatus.split(",");

        IPage<OrderInfoBO> ipage = super.baseMapper.findOrderByStatus(new Page<>(pageNo, 3), orderStatusArr, buyerId);

        return PageResult.pageInfo(ipage.getCurrent(),ipage.getPages(), DozerUtil.mapList(ipage.getRecords(), OrderInfoDTO.class));
    }
}
