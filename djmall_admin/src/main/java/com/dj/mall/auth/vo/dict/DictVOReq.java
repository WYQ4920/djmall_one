package com.dj.mall.auth.vo.dict;

import lombok.Data;

/**
 * @Author zhengyk
 * @Date 2021/1/21 11:32
 */
@Data
public class DictVOReq {

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
