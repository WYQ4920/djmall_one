package com.dj.mall.dict.bo.sku;

import lombok.Data;

import java.util.List;

/**
 * @Author zhengyk
 * @Date 2021/1/24 21:10
 */
@Data
public class SkuBO {

    /**
     *商品字典名
     */
    private String DictName;

    /**
     *属性名
     */
    private String AttrName;

    /**
     *sku名
     */
    private String productType;

    /**
     * 属性 ids(String)
     */
    private String attrIds;

    /**
     * 属性 ids (Integer)
     */
    private Integer[] attrIdArr;

    /**
     * 属性 id
     */
    private Integer attrId;

}
