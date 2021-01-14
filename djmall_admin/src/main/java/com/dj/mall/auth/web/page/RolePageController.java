package com.dj.mall.auth.web.page;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author WYQ
 * @Date 2021/1/14 15:02
 * 角色表 -> 操作跳转路径控制层
 */

@Controller
@RequestMapping("/role/")
public class RolePageController {

    /**
     * 去展示
     */
    @RequestMapping("toShow")
    public String toShow(){
        return "permission/role/show";
    }

    /**
     * 去新增
     */
    @RequestMapping("toAdd")
    public String toAdd(){
        return "permission/role/add";
    }

    /**
     * 去修改
     */
    @RequestMapping("toUpdate")
    public String toUpdate(Integer id, ModelMap map){
        map.put("id", id);
        return "permission/role/update";
    }

}
