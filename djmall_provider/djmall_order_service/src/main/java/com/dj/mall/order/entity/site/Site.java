package com.dj.mall.order.entity.site;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author zhengyk
 * @Date 2021/1/4 14:03
 */
@Data
@Accessors(chain = true)
@TableName("djmall_user_site")
public class Site implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private String userName;

    private String userPhone;

    private String sheng;

    private String shi;

    private String qu;

    private String details;

}
