package com.dj.mall.platform.web.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.api.user.UserApi;
import com.dj.mall.auth.dto.role.RoleDTO;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.common.constant.UserConstant;
import com.dj.mall.common.util.DozerUtil;
import com.dj.mall.common.util.PasswordSecurityUtil;
import com.dj.mall.dict.api.DictApi;
import com.dj.mall.dict.dto.DictDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/user/")
@Controller
public class UserPageController {



    @Reference
    private UserApi userApi;

    @Reference
    private DictApi dictApi;

    @RequestMapping("toLogin")
    public String toLogin() {
        return "user/login";
    }

    @RequestMapping("toAdd")
    public String toAdd(Model model) throws Exception {
        model.addAttribute("salt", PasswordSecurityUtil.generateSalt());
        return "user/add";
    }

    @RequestMapping("toShow")
    public String toShow(Model model) throws Exception {
        UserDTO user = userApi.findUserById(13);
        List<DictDTO> sexList = dictApi.findByParentCode(UserConstant.USER_SEX_PARENT_CODE);
        model.addAttribute("user", user);
        model.addAttribute("sexList", sexList);
        return "user/show";
    }

}
