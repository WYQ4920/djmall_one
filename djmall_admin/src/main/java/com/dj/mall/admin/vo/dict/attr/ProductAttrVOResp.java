package com.dj.mall.admin.vo.dict.attr;

import com.dj.mall.admin.vo.dict.attr.value.ProductAttrValueVOReq;
import lombok.Data;

import java.util.List;

/**
 * @Author WYQ
 * @Date 2021/1/24 12:11
 */

@Data
public class ProductAttrVOResp {

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
