package com.dj.mall.admin.web.auth.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.role.RoleVOResp;
import com.dj.mall.admin.vo.user.UserVOResp;
import com.dj.mall.auth.api.role.RoleApi;
import com.dj.mall.auth.api.user.UserApi;
import com.dj.mall.auth.dto.role.RoleDTO;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.common.util.DozerUtil;
import com.dj.mall.common.util.PasswordSecurityUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
    @RequiresPermissions("USER_MANAGER")
    public String toShow() {
        return "user/show";
    }

    @RequestMapping("toUpdate/{id}")
    @RequiresPermissions("USER_UPDATE_BTN")
    public String toUpdate(@PathVariable Integer id, Model model) throws Exception {
        UserDTO userDTO = userApi.findUserById(id);
        model.addAttribute("user", DozerUtil.map(userDTO, UserVOResp.class));
        return "user/update";
    }

    @RequestMapping("toAdd")
    public String toAdd(Model model) throws Exception {
        model.addAttribute("salt", PasswordSecurityUtil.generateSalt());
        List<RoleDTO> roleList = roleApi.getRoleList();
        model.addAttribute("roleList", DozerUtil.mapList(roleList, RoleVOResp.class));
        return "user/add";
    }

    @RequestMapping("toGiveRole")
    @RequiresPermissions("USER_ADD_ROLE_BTN")
    public String totoGiveRole(Integer userId, Model model) throws Exception {
        model.addAttribute("userId",userId);
        Integer roleId = userApi.findRoleByUserId(userId);
        model.addAttribute("roleId",roleId);
        List<RoleDTO> roleList= userApi.findAllRole();
        model.addAttribute("roleList",roleList);
        return "user/give_role";
    }

    @RequestMapping("exit")
    public String exit(HttpSession session) {
        session.removeAttribute("user");
        session.invalidate();
        return "user/login";
    }


}
