package com.dj.mall.dict.dto.sku;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author zhengyk
 * @Date 2021/1/24 21:10
 */
@Data
public class SkuDTO implements Serializable {

    /**
     * 商品类型
     */
    private String productType;

    /**
     *商品字典名
     */
    private String DictName;

    /**
     *属性名
     */
    private String AttrName;


    /**
     * 属性 ids(String)
     */
    private String attrIds;
}
