package com.dj.mall.order.mapper.orderInfo;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.mall.order.bo.OrderInfoBO;
import com.dj.mall.order.entity.orderInfo.OrderInfoEntity;
import org.apache.ibatis.annotations.Param;

/**
 * @Author zhengyk
 * @Date 2021/2/5 19:57
 */
public interface OrderInfoMapper extends BaseMapper<OrderInfoEntity> {

    IPage<OrderInfoBO> findOrderByStatus(Page<Object> objectPage, @Param("orderStatusArr") String[] orderStatusArr, @Param("buyerId") Integer buyerId);
}
