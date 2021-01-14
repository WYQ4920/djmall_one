package com.dj.mall.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author zhengyk
 * @Date 2021/1/14 15:07
 */
<<<<<<< HEAD
=======
@Data
>>>>>>> origin/master
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
    private String ResourceName;

    /**
     * 资源路径
     */
    private String url;

    /**
     * 父级ID
     */
    private Integer parentId;


}
