package com.dj.mall.dict.dto.freight;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author WYQ
 * @Date 2021/1/21 22:35
 */

@Data
public class FreightDTO implements Serializable {

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
