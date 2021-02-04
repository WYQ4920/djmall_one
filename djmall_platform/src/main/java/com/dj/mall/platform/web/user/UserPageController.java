package com.dj.mall.platform.web.user;

import com.dj.mall.auth.dto.role.RoleDTO;
import com.dj.mall.common.util.DozerUtil;
import com.dj.mall.common.util.PasswordSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/user/")
@Controller
public class UserPageController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("toLogin")
    public String toLogin() {
        return "user/login";
    }

    @RequestMapping("toAdd")
    //@RequiresPermissions("USER_REGISTER_BTN")
    public String toAdd(Model model) throws Exception {
        model.addAttribute("salt", PasswordSecurityUtil.generateSalt());

        model.addAttribute("userSexMap", redisTemplate.opsForHash().entries("USER_SEX"));
        return "user/add";
    }


}
