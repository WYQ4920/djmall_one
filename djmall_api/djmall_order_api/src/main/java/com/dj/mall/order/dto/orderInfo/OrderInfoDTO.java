package com.dj.mall.order.dto.orderInfo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单表-子
 * @Author zhengyk
 * @Date 2021/2/5 17:56
 */
@Data
public class OrderInfoDTO implements Serializable {

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 父订单号
     */
    private String parentOrderNo;

    /**
     * 买家ID
     */
    private Integer buyerId;

    /**
     * 商户ID
     */
    private Integer businessId;

    /**
     * 商品ID
     */
    private Integer productId;

    /**
     * 订单总金额
     */
    private BigDecimal totalMoney;

    /**
     * 实际付款总金额
     */
    private BigDecimal totalPayMoney;

    /**
     * 总运费
     */
    private BigDecimal totalFreight;

    /**
     * 总购买数量
     */
    private Integer totalBuyCount;

    /**
     * 支付方式
     */
    private String payType;

    /**
     * 收货信息-省
     */
    private String receiverProvince;

    /**
     * 收货信息-城市
     */
    private String receiverCity;

    /**
     * 收货信息-区县
     */
    private String receiverCounty;

    /**
     * 收货信息-收货人
     */
    private String receiverName;

    /**
     * 收货信息-手机号
     */
    private String receiverPhone;

    /**
     * 收货信息-地址明细
     */
    private String receiverDetail;

    /**
     *  订单状态
     */
    private String orderStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     *支付时间
     */
    private LocalDateTime payTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 商品名
     */
    private String productName;

    /**
     * 邮费
     */
    private String productPostage;

    /**
     * 商品属性属性
     */
    private String skuInfo;

    /**
     * 支付方式
     */
    private String payTypeShow;

    /**
     * 订单状态
     */
    private String orderStatusShow;

    /**
     * 折扣
     */
    private String skuRate;




}
