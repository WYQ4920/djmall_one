package com.dj.mall.admin.vo.product;

import lombok.Data;

import java.util.Date;

/**
 * @Author WYQ
 * @Date 2021/2/1 11:01
 */

@Data
public class ProductSkuVOResp {

    /**
     * SKU ID
     */
    private Integer id;

    /**
     * 商品ID
     */
    private Integer productId;

    /**
     * sku价格
     */
    private Double skuPrice;

    /**
     * sku库存
     */
    private Integer skuCount;

    /**
     * SKU折扣,0表示无折扣
     */
    private Integer skuRate;

    /**
     * SKU状态[0下架,1上架]
     */
    private String skuStatus;

    /**
     * SKU属性值名集合[name1:name2]
     */
    private String skuName;

    /**
     * 商户ID
     */
    private Integer businessId;

    /**
     * 是否默认
     */
    private String isDefault;

    /**
     * 更新时间
     */
    private Date updateTime;

}
