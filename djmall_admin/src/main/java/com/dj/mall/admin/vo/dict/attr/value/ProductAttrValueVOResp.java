package com.dj.mall.admin.vo.dict.attr.value;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

/**
 * @Author WYQ
 * @Date 2021/1/24 12:13
 */

@Data
public class ProductAttrValueVOResp {

    /**
     * 商品属性值id
     */
    private Integer id;

    /**
     * 商品属性id
     */
    private Integer attrId;

    /**
     * 商品属性值
     */
    private List<ProductAttrValueVOResp> attrValueList;
}
