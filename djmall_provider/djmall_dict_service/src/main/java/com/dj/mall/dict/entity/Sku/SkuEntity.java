package com.dj.mall.dict.entity.Sku;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author zhengyk
 * @Date 2021/1/24 21:11
 */
@Data
@TableName("djmall_dict_product_sku_gm")
public class SkuEntity {

    /**
     * skuId
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 商品类型
     */
    private String productType;

    /**
     * 属性 id
     */
    private Integer attrId;
}
