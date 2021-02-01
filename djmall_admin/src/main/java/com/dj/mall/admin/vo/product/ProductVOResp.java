package com.dj.mall.admin.vo.product;

import lombok.Data;

/**
 * @Author WYQ
 * @Date 2021/2/1 10:59
 */

@Data
public class ProductVOResp {

    /**
     * 商品ID
     */
    private Integer id;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品类型
     */
    private String productType;

    /**
     * 商品状态(上、下架)
     */
    private String productStatus;

    /**
     * 商品邮费
     */
    private String productPostage;

    /**
     * 商品图片
     */
    private String productImg;

    /**
     * 商品描述
     */
    private String productDes;

    /**
     * 商品点赞量
     */
    private Integer productGiveLike;

    /**
     * 商品订单量
     */
    private Integer productOrderCount;

    /**
     * 商品状态码(是否删除)
     */
    private Integer isDel;

}
