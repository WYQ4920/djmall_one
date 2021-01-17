package com.dj.mall.auth.vo.role;

import lombok.Data;

/**
 * @Author WYQ
 * @Date 2021/1/17 20:45
 */

@Data
public class TreeDataVOResp {

    /**
     * 资源id
     */
    private Integer id;

    /**
     * 父级id
     */
    private Integer parentId;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 复选框默认打开
     */
    private Boolean open = true;

    /**
     * 复选框选中状态
     */
    private Boolean checked;
}
