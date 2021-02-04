package com.dj.mall.platform.web.page;

import org.springframework.stereotype.Controller;
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
