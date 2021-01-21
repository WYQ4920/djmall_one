package com.dj.mall.dict.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author zhengyk
 * @Date 2021/1/21 14:48
 */
@Data
public class DictDTO implements Serializable {

    /**
     * 字典数据code
     */
    private String code;

    /**
     * 字典数据父级code
     */
    private String parentCode;

    /**
     * 字典数据名
     */
    private String dictName;

}
