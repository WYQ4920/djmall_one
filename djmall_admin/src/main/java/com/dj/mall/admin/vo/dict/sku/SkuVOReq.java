package com.dj.mall.admin.vo.dict.sku;

import lombok.Data;

/**
 * @Author zhengyk
 * @Date 2021/1/24 21:03
 */
@Data
public class SkuVOReq {

    /**
     * 商品类型
     */
    private String productType;

    /**
     *商品字典名
     */
    private String dictName;

    /**
     *属性名
     */
    private String attrName;

    /**
     * 属性 ids(String)
     */
    private String attrIds;


}
