package com.dj.mall.dict.dto.attr;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author WYQ
 * @Date 2021/1/24 12:16
 */

@Data
public class ProductAttrValueDTO implements Serializable {

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
