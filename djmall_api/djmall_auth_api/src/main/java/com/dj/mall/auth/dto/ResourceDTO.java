package com.dj.mall.auth.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors
public class ResourceDTO {
    /**
     * 资源ID
     */
    private Integer id;

    /**
     * 资源名
     */
    private String ResourceName;

    /**
     * 资源路径
     */
    private String url;

    /**
     * 父级ID
     */
    private Integer parentId;

    /**
     * 状态
     * 0:未删除
     * 1:已删除
     */
    private Integer isDel;


}
