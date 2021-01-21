package com.dj.mall.dict.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author zhengyk
 * @Date 2021/1/21 15:01
 */
@Data
@TableName("djmall_dict")
public class DictEntity {

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
