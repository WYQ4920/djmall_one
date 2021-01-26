package com.dj.mall.dict.bo.attr;

import com.dj.mall.dict.entity.attr.ProductAttrValueEntity;
import lombok.Data;

import java.util.List;

/**
 * @Author WYQ
 * @Date 2021/1/24 12:45
 */

@Data
public class ProductAttrBO {

    /**
     * 商品属性id
     */
    private Integer id;

    /**
     * 商品属性名
     */
    private String attrName;

    /**
     * 商品属性值
     */
    private String attrValue;

}
