package com.dj.mall.auth.web.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/res/")
public class ResourcePageController {

<<<<<<< HEAD
     @RequestMapping("toHome")
    public String toHome(){
=======
    @RequestMapping("toShowResZtree")
    public String toShowResZtree() throws Exception{
        return "res/show";
    }

>>>>>>> origin/master

         return "index/left";
     }


}
