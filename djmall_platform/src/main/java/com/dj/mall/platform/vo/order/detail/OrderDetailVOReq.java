package com.dj.mall.platform.vo.order.detail;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单-商品-明细表
 * @Author zhengyk
 * @Date 2021/2/5 17:38
 */
@Data
public class OrderDetailVOReq {

    /**
     * 明细ID
     */
    private Integer id;

    /**
     * 父订单号
     */
    private String parentOrderNo;

    /**
     * 子订单号
     */
    private String childOrderNo;

    /**
     * 买家ID
     */
    private Integer buyerId;

    /**
     * 商品ID
     */
    private Integer productId;

    /**
     * 商户ID
     */
    private Integer businessId;

    /**
     * SKUID-针对再次购买时使用
     */
    private Integer skuId;

    /**
     * SKU信息(iphone-红色-64G)
     */
    private String skuInfo;

    /**
     * SKU价格
     */
    private BigDecimal skuPrice;

    /**
     * SK折扣
     */
    private Integer skuRate;

    /**
     * 购买数量
     */
    private Integer buyCount;

    /**
     * 支付金额（含运费）
     */
    private BigDecimal payMoney;

    /**
     * 运费
     */
    private BigDecimal freightPrice;

    /**
     * 创建时间
     */
    private LocalDateTime create_time;






}
