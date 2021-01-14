package com.dj.mall.auth.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.api.UserApi;
import com.dj.mall.auth.dto.UserDTO;
import com.dj.mall.auth.vo.UserVOReq;
import com.dj.mall.auth.vo.UserVOResp;
import com.dj.mall.common.base.ResultModel;
import com.dj.mall.common.util.DozerUtil;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/user/")
@RestController
public class UserController {

    /**
     * 启动时不检查
     */
    @Reference(check = false)
    private UserApi userApi;

    /**
     * 登录
     * @param userName
     * @param userPwd
     * @return
     */
    @GetMapping("login")
    public ResultModel<Object> login(String userName, String userPwd, HttpSession session) throws Exception {
        Assert.hasText(userName, "用户名不能为空");
        Assert.hasText(userPwd, "密码不能为空");
        UserDTO userDTO = userApi.findUserByNameAndPwd(userName, userPwd);
        if (userDTO == null) {
            return new ResultModel<>().error("用户名或密码不正确");
        }
        session.setAttribute("user",userDTO);
        return new ResultModel<>().success();
    }

    /**
     * 用户展示
     * @param userVOReq
     * @return
     */
    @PostMapping("list")
    public ResultModel<Object> list(UserVOReq userVOReq){
        List<UserDTO> list = userApi.findAll(DozerUtil.map(userVOReq,UserDTO.class));
        return new ResultModel<>().success(DozerUtil.mapList(list, UserVOResp.class));
    }






}
