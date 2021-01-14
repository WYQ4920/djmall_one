package com.dj.mall.auth.web.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/res/")
public class ResourcePageController {

     @RequestMapping("toHome")
    public String toHome(){

         return "index/left";
     }


}
