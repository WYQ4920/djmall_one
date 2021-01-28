package com.dj.mall.dict.dto.attr;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author WYQ
 * @Date 2021/1/24 12:16
 */

@Data
public class ProductAttrDTO implements Serializable {

    /**
     * 商品属性id
     */
    private Integer id;

    /**
     * 商品属性名
     */
    private String attrName;

    /**
     * 商品属性值id
     */
    private Integer attrId;

    /**
     * 商品属性值id
     */
    private String attrValueId;

    /**
     * 商品属性值
     */
    private String attrValue;

    /**
     * 商品属性值集合
     */
    private List<ProductAttrValueDTO> attrValueList;
}
