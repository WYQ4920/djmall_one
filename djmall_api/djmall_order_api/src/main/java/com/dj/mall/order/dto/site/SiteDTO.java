package com.dj.mall.order.dto.site;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author zhengyk
 * @Date 2021/1/4 14:03
 */
@Data
@Accessors(chain = true)

public class SiteDTO implements Serializable {

    private Integer id;

    private Integer userId;

    private String userName;

    private String userPhone;

    private String sheng;

    private String shi;

    private String qu;

    private String details;

}
