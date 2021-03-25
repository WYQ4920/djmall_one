package com.dj.mall.platform.vo.order;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单表-主
 * @Author zhengyk
 * @Date 2021/2/5 16:29
 */
@Data
public class OrderVOResp {

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 买家ID
     */
    private String buyerId;

    /**
     * 订单总金额
     */
    private BigDecimal totalMoney;

    /**
     * 实付总金额
     */
    private BigDecimal totalPayMoney;

    /**
     * 总运费
     */
    private BigDecimal totalFreight;

    /**
     *总购买数量
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
     * 订单状态展示
     */
    private String orderStatusShow;

    /**
     * 支付方式展示
     */
    private String payTypeShow;


    /**
     * 商品名
     */
    private String productName;




}
