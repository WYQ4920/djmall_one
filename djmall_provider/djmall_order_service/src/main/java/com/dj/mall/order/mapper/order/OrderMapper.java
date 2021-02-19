package com.dj.mall.order.mapper.order;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.mall.order.bo.OrderBO;
import com.dj.mall.order.entity.order.OrderEntity;
import org.apache.ibatis.annotations.Param;

/**
 * @Author zhengyk
 * @Date 2021/2/5 19:56
 */
public interface OrderMapper extends BaseMapper<OrderEntity> {


    IPage<OrderBO> findOrderByStatus(Page<Object> objectPage, @Param("orderStatus") String orderStatus, @Param("buyerId") Integer buyerId);
}
