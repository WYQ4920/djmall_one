package com.dj.mall.admin.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index/")
public class IndexPageController {

    @RequestMapping("/toIndex")
    public String toIndex() {
        return "index/index";
    }

    @RequestMapping("toTop")
    public String toTop() {
        return "index/top";
    }

    @RequestMapping("toLeft")
    public String toLeft() {
        return "index/left";
    }

    @RequestMapping("toRight")
    public String toRight() throws Exception {
        return "index/right";
    }
}
