package com.dj.mall.order.entity.area;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author zhengyk
 * @Date 2021/1/4 14:03
 */
@Data
@Accessors(chain = true)
@TableName("djmall_area")
public class Area {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String areaName;
    private String areaPinyin;
    private Integer parentId;

}
