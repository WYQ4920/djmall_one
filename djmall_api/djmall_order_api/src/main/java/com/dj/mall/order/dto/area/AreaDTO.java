package com.dj.mall.order.dto.area;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author zhengyk
 * @Date 2021/1/4 14:07
 */
@Data
public class AreaDTO implements Serializable {

    private Integer id;

    private String areaName;
    private String areaPinyin;
    private Integer parentId;
}
