package com.dj.mall.admin.vo.resource;

import lombok.Data;

@Data
public class ResourceVOResp {
    /**
     * 资源ID
     */
    private Integer id;

    /**
     * 资源名
     */
    private String ResourceName;

    /**
     * 编码
     */
    private String ResourceCode;

    /**
     * 类型
     */
    private String ResourceType;

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
