package com.dj.mall.auth.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author Zjj
 */
@Controller
@RequestMapping("/zjj/index/")
public class ZjjIndexController {

    @RequestMapping("toIndex")
    public String toIndex() {
        return "index/zjj/index";
    }

    @RequestMapping("toTop")
    public String toTop() {
        return "index/zjj/top";
    }

    @RequestMapping("toLeft")
    public String toLeft() {
        return "index/zjj/left";
    }

    @RequestMapping("toRight")
    public String toRight() throws Exception {
        return "index/zjj/right";
    }


}
