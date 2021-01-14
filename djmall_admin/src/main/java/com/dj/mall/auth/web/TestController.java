package com.dj.mall.auth.web;

import com.alibaba.dubbo.config.annotation.Reference;

import com.dj.mall.auth.api.UserApi;
import com.dj.mall.auth.dto.UserDTO;
import com.dj.mall.common.base.ResultModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @Reference
    private UserApi userApi;

    @GetMapping("/toTest")
    public String toTest() {
        return "test";
    }

    @GetMapping("/test")
    @ResponseBody
    public ResultModel test() {
        UserDTO userDTO = userApi.getUser(1);
        return new ResultModel().success(userDTO);
    }

}
