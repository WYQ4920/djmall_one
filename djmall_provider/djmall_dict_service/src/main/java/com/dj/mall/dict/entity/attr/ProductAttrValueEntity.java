package com.dj.mall.dict.entity.attr;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author WYQ
 * @Date 2021/1/24 12:05
 */

@Data
@TableName("djmall_dict_product_attr_value")
public class ProductAttrValueEntity {

    /**
     * 商品属性值id
     */
    @TableId(type = IdType.AUTO)
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
