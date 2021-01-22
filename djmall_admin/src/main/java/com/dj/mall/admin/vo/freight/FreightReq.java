package com.dj.mall.admin.vo.freight;

import lombok.Data;

/**
 * @Author WYQ
 * @Date 2021/1/21 22:38
 */

@Data
public class FreightReq {

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
