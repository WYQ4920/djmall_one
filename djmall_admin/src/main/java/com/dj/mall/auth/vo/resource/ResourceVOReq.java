package com.dj.mall.auth.vo.resource;

import lombok.Data;

import java.util.List;

@Data
public class ResourceVOReq {
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
     * 编码
     */
    private String ResourceCode;

    /**
     * 类型
     */
    private String ResourceType;

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
    /**
     * 资源id集合
     */
    private List<Integer> resourceIds;

    /**
     * 资源ID集合
     */
    private List<Integer> resourceIds;
}
