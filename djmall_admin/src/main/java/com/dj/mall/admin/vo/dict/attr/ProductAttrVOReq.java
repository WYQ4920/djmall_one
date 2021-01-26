package com.dj.mall.admin.vo.dict.attr;

import com.dj.mall.admin.vo.dict.attr.value.ProductAttrValueVOReq;
import com.dj.mall.dict.dto.attr.ProductAttrValueDTO;
import lombok.Data;

import java.util.List;

/**
 * @Author WYQ
 * @Date 2021/1/24 12:10
 */

@Data
public class ProductAttrVOReq {

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
