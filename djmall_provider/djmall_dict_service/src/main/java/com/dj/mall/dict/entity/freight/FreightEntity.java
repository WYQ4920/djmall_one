package com.dj.mall.dict.entity.freight;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author WYQ
 * @Date 2021/1/21 22:30
 */

@Data
@TableName("djmall_freight")
public class FreightEntity {

    /**
     * 运费id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 字典编码
     */
    private String dictCode;

    /**
     * 运费
     */
    private String freight;
}
