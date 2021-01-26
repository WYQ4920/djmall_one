package com.dj.mall.dict.bo.attr;

import lombok.Data;

/**
 * @Author WYQ
 * @Date 2021/1/25 0:11
 */

@Data
public class ProductAttrValueBO {

    /**
     * 商品属性值id
     */
    private Integer id;

    /**
     * 商品属性id
     */
    private Integer attrId;

    /**
     * 商品属性值
     */
    private String attrValue;
}
