package com.dj.mall.auth.web.page;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author WYQ
 * @Date 2021/1/14 15:02
 */

@Controller
@RequestMapping("/role/")
public class RolePageController {

    /**
     * 去展示
     */
    public String toShow(){
        return "role/show";
    }

}
