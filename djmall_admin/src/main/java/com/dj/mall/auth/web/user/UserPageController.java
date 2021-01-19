package com.dj.mall.auth.web.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.api.role.RoleApi;
import com.dj.mall.auth.api.user.UserApi;
import com.dj.mall.auth.dto.role.RoleDTO;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.auth.vo.role.RoleVOResp;
import com.dj.mall.auth.vo.user.UserVOResp;
import com.dj.mall.common.util.DozerUtil;
import com.dj.mall.common.util.PasswordSecurityUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/user/")
@Controller
public class UserPageController {

    @Reference
    private UserApi userApi;

    @Reference
    private RoleApi roleApi;

    @RequestMapping("toLogin")
    public String toLogin() {
        return "user/login";
    }

    @RequestMapping("toShow")
    public String toShow() {
        return "user/show";
    }

    @RequestMapping("toUpdate/{id}")
    public String toUpdate(@PathVariable Integer id, Model model) throws Exception {
        UserDTO userDTO = userApi.findUserById(id);
        model.addAttribute("user", DozerUtil.map(userDTO, UserVOResp.class));
        return "user/update";
    }

    @RequestMapping("toAdd")
    public String toAdd(Model model) throws Exception {
        model.addAttribute("salt", PasswordSecurityUtil.generateSalt());
        List<RoleDTO> roleList = roleApi.getRoleList();
        model.addAttribute("roleList",DozerUtil.mapList(roleList, RoleVOResp.class));
        return "user/add";
    }

    @RequestMapping("exit")
    public String exit(HttpSession session){
        session.removeAttribute("user");
        session.invalidate();
        return "user/login";
    }


}
