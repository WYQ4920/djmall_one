package com.dj.mall.dict.entity.attr;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

/**
 * @Author WYQ
 * @Date 2021/1/24 12:02
 */

@Data
@TableName("djmall_dict_product_attr")
public class ProductAttrEntity {

    /**
     * 商品属性id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 商品属性名
     */
    private String attrName;

    /**
     * 商品属性值
     */
    @TableField(exist = false)
    private String attrValue;

}
