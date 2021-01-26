package com.dj.mall.admin.vo.freight;

import lombok.Data;

/**
 * @Author WYQ
 * @Date 2021/1/21 22:50
 */

@Data
public class FreghtResp {

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
