package com.dj.mall.platform.web.platform;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/djmall_platform/")
@Controller
public class PfmPageController {
    @RequestMapping("toShow")
    public String toShow() {
        return "djmall_platform/show";
    }
}
