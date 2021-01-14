package com.dj.mall.auth.web.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/res/")
public class ResourcePageController {



    @Reference(check = false)
    private ResourceApi resourceApi;

    @RequestMapping("toShowResZtree")
    public String toShowResZtree() throws Exception {
        return "index/left";
    }

}



