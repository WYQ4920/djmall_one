package com.dj.mall.platform.web.area;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author zhengyk
 * @Date 2021/1/4 10:18
 */
@Controller
@RequestMapping("/areas/")
public class AreaPageController {

    @RequestMapping("toShow")
    public String toShow() throws Exception{
        return "area/show";
    }

}
