package com.dj.mall.platform.web.platform;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/djmall_platform/")
@RestController
public class PfmController {

    @RequestMapping("toShow")
    public String toShow() {
        return "djmall_platform/show";
    }


}
