package com.dj.mall.admin.web.auth.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.role.RoleVOResp;
import com.dj.mall.admin.vo.user.UserVOResp;
import com.dj.mall.auth.api.role.RoleApi;
import com.dj.mall.auth.api.user.UserApi;
import com.dj.mall.auth.dto.role.RoleDTO;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.common.base.ResultModel;
import com.dj.mall.common.constant.UserConstant;
import com.dj.mall.common.util.DozerUtil;
import com.dj.mall.common.util.PasswordSecurityUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RequestMapping("/user/")
@Controller
public class UserPageController {

    @Reference
    private UserApi userApi;

    @Reference
    private RoleApi roleApi;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("toLogin")
    public String toLogin() {
        return "user/login";
    }

    @RequestMapping("toShow")
    @RequiresPermissions("USER_MANAGER")
    public String toShow(Model model) throws Exception {
        // 角色集合
        List<RoleDTO> roleList = roleApi.getRoleList();
        model.addAttribute("roleList", DozerUtil.mapList(roleList, RoleVOResp.class));
        // redis
        HashOperations hashOperations = redisTemplate.opsForHash();
        // 获取性别
        Map userSexMap = hashOperations.entries("USER_SEX");
        model.addAttribute("userSexMap", userSexMap);
        // 获取用户状态
        model.addAttribute("userStatusMap", hashOperations.entries("USER_STATUS"));
        return "user/show";
    }

    @RequestMapping("toUpdate/{id}")
    @RequiresPermissions("USER_UPDATE_BTN")
    public String toUpdate(@PathVariable Integer id, Model model) throws Exception {
        UserDTO userDTO = userApi.findUserById(id);
        model.addAttribute("user", DozerUtil.map(userDTO, UserVOResp.class));
        model.addAttribute("userSexMap", redisTemplate.opsForHash().entries("USER_SEX"));
        return "user/update";
    }

    @RequestMapping("toAdd")
    //@RequiresPermissions("USER_REGISTER_BTN")
    public String toAdd(Model model) throws Exception {
        model.addAttribute("salt", PasswordSecurityUtil.generateSalt());
        List<RoleDTO> roleList = roleApi.getRoleList();
        model.addAttribute("roleList", DozerUtil.mapList(roleList, RoleVOResp.class));
        model.addAttribute("userSexMap", redisTemplate.opsForHash().entries("USER_SEX"));
        return "user/add";
    }

    @RequestMapping("toGiveRole")
    @RequiresPermissions("USER_ADD_ROLE_BTN")
    public String totoGiveRole(Integer userId, Model model) throws Exception {
        model.addAttribute("userId", userId);
        Integer roleId = userApi.findRoleByUserId(userId);
        model.addAttribute("roleId", roleId);
        List<RoleDTO> roleList = userApi.findAllRole();
        model.addAttribute("roleList", roleList);
        return "user/give_role";
    }

    @RequestMapping("exit")
    public String exit(HttpSession session) {
        session.removeAttribute(UserConstant.USER_SESSION);
        //session.invalidate();
        return "user/login";
    }

    /**
     * 商户激活
     * @param id 用户id
     * @return
     * @throws Exception
     */
    @RequestMapping("active")
    public String active(Integer id) throws Exception {
        UserDTO userDTO = UserDTO.builder().id(id).userStatus(UserConstant.USER_STATUS_ACTIVE).build();
        userApi.updateUser(userDTO);
        return "user/active";
    }

}
