package com.dj.mall.auth.entity.resource;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author
 * @Date 2021/1/14 15:07
 */

@Data
@TableName("djmall_auth_resource")
public class ResourceEntity {

    /**
     * 资源ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 资源名
     */
    private String resourceName;

    /**
     * 资源路径
     */
    private String url;

    /**
     * 编码
     */
    private String resourceCode;

    /**
     * 类型
     */
    private String resourceType;

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
