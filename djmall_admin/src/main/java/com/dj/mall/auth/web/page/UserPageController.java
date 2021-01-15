package com.dj.mall.auth.web.page;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.api.UserApi;
import com.dj.mall.auth.dto.UserDTO;
import com.dj.mall.auth.vo.UserVOResp;
import com.dj.mall.common.util.DozerUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RequestMapping("/user/")
@Controller
public class UserPageController {

    @Reference(check = false)
    private UserApi userApi;

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
    public String toAdd() {
        return "user/add";
    }

    /**
     * 用户退出登录
     */
    @GetMapping("exitUserLogin")
    public ModelAndView exitUserLogin(HttpSession session) {
        session.removeAttribute("user");
        ModelAndView view = new ModelAndView();
        view.setViewName("toLogin");
        return view;
    }
}
