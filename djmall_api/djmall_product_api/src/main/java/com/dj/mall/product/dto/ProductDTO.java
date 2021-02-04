package com.dj.mall.product.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author WYQ
 * @Date 2021/2/1 11:05
 */

@Data
public class ProductDTO implements Serializable {

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

    /**
     * 图片byte数组
     */
    private byte[] img;

    /**
     * 分类集合
     */
    private String[] classifyList;

    /**
     * 商品集合
     */
    private List<ProductDTO> productDTOList;

    /**
     * 当前页
     */
    private Integer pages;
}
