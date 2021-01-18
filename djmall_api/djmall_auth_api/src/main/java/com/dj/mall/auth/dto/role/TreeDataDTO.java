package com.dj.mall.auth.dto.role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author WYQ
 * @Date 2021/1/17 21:17
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TreeDataDTO implements Serializable {

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
