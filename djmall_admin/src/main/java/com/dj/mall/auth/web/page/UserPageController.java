package com.dj.mall.auth.web.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user/")
@Controller
public class UserPageController {

    @RequestMapping("toLogin")
    public String toLogin(){
        return "user/login";
    }
}
