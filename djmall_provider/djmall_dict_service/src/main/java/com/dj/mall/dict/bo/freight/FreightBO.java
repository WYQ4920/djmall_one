package com.dj.mall.dict.bo.freight;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Author WYQ
 * @Date 2021/1/21 23:00
 */

@Data
public class FreightBO {

    /**
     * 运费id
     */
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
