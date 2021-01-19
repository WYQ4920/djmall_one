package com.dj.mall.auth.web.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.api.user.UserApi;
import com.dj.mall.auth.dto.res.ResourceDTO;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.auth.vo.resource.ResourceVOResp;
import com.dj.mall.auth.vo.user.UserVOReq;
import com.dj.mall.auth.vo.user.UserVOResp;
import com.dj.mall.common.base.BusinessException;
import com.dj.mall.common.base.ResultModel;
import com.dj.mall.common.constant.UserConstant;
import com.dj.mall.common.util.DozerUtil;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/user/")
@RestController
public class UserController {

    @Reference
    private UserApi userApi;

    /**
     * 登录
     *
     * @param userName
     * @param userPwd
     * @return
     */
    @PostMapping("login")
    public ResultModel<Object> login(String userName, String userPwd, HttpSession session) throws Exception {
        Assert.hasText(userName, "用户名不能为空");
        Assert.hasText(userPwd, "密码不能为空");
        UserDTO userDTO = userApi.findUserByNameAndPwd(userName, userPwd);
        session.setAttribute("user", userDTO);
        return new ResultModel<>().success();
    }

    /**
     * 用户展示
     *
     * @param userVOReq
     * @return
     */
    @PostMapping("list")
    public ResultModel<Object> list(UserVOReq userVOReq) throws Exception {
        List<UserDTO> list = userApi.findUserAll(DozerUtil.map(userVOReq, UserDTO.class));
        return new ResultModel<>().success(DozerUtil.mapList(list, UserVOResp.class));
    }

    /**
     * 新增用户
     *
     * @param userVOReq
     * @return
     * @throws Exception
     */
    @PostMapping("add")
    public ResultModel<Object> add(UserVOReq userVOReq, HttpSession session) throws Exception {
        Assert.hasText(userVOReq.getUserName(), "用户名不能为空");
        Assert.hasText(userVOReq.getUserPwd(), "用户密码不能为空");
        Assert.hasText(userVOReq.getUserPhone(), "用户手机号不能为空");
        userApi.addUser(DozerUtil.map(userVOReq, UserDTO.class));
        return new ResultModel<>().success();
    }

    /**
     * 修改用户
     *
     * @param userVOReq
     * @return
     */
    @PutMapping("update")
    public ResultModel<Object> update(UserVOReq userVOReq) throws BusinessException {
        Assert.hasText(userVOReq.getUserName(), "用户名不能为空");
        userApi.updateUser(DozerUtil.map(userVOReq, UserDTO.class));
        return new ResultModel<>().success();
    }

    /**
     * 获取用户密码盐
     *
     * @param userName
     * @return
     * @throws Exception
     */
    @PostMapping("getSalt")
    public ResultModel getSalt(String userName) throws Exception {
        Assert.hasText(userName, "用户名不能为空");
        UserDTO userDTO = userApi.getSalt(userName);
        return new ResultModel().success(DozerUtil.map(userDTO, UserVOResp.class).getSalt());
    }

    /**
     * 用户名查重
     *
     * @param userName
     * @return
     * @throws Exception
     */
    @RequestMapping("checkUserName")
    public boolean checkUserName(String userName) throws Exception {
        return userApi.checkUserName(userName);
    }

    /**
     * 用户邮箱查重
     *
     * @param userEmail
     * @return
     * @throws Exception
     */
    @RequestMapping("checkUserEmail")
    public boolean checkUserEmail(String userEmail) throws Exception {
        return userApi.checkUserEmail(userEmail);
    }

    /**
     * 用户手机号查重
     *
     * @param userPhone
     * @return
     * @throws Exception
     */
    @RequestMapping("checkUserPhone")
    public boolean checkUserPhone(String userPhone) throws Exception {
        return userApi.checkUserPhone(userPhone);
    }

    /**
     * 删除用户
     * @param userVOReq
     * @return
     * @throws Exception
     */
    @PostMapping("del")
    public ResultModel del(UserVOReq userVOReq) throws Exception {
        userApi.del(DozerUtil.map(userVOReq, UserDTO.class));
        return new ResultModel().success();
    }
}
