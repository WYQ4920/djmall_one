package com.dj.mall.platform.vo.area;

import lombok.Data;

/**
 * @Author zhengyk
 * @Date 2021/1/4 14:07
 */
@Data
public class AreaVOResp {

    private Integer id;

    private String areaName;
    private String areaPinyin;
    private Integer parentId;
}
