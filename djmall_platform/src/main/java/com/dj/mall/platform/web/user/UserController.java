package com.dj.mall.platform.web.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.api.user.UserApi;
import com.dj.mall.auth.dto.res.ResourceDTO;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.common.base.BusinessException;
import com.dj.mall.common.base.ResultModel;
import com.dj.mall.common.constant.UserConstant;
import com.dj.mall.common.util.CodeTest;
import com.dj.mall.common.util.DozerUtil;
import com.dj.mall.platform.vo.user.UserVOReq;
import com.dj.mall.platform.vo.user.UserVOResp;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

;import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/user/")
@RestController
public class UserController {

    @Reference
    private UserApi userApi;

    /**
     * 登录
     *
     * @param userNPE
     * @param userPwd
     * @return
     */
    @PostMapping("login")
    public ResultModel<Object> login(String userNPE, String userPwd) throws Exception, BusinessException {
        Assert.hasText(userNPE, "用户名/手机号/不能为空");
        Assert.hasText(userPwd, "密码不能为空");
        UserDTO userDTO = userApi.findUserByNPEAndPwd(userNPE, userPwd);
        return new ResultModel<>().success();
    }

    /**
     * 新增用户
     *
     * @param userVOReq
     * @return
     * @throws Exception
     */
    @PostMapping("add")
    public ResultModel<Object> add(UserVOReq userVOReq) throws BusinessException, Exception {
        Assert.hasText(userVOReq.getUserName(), "用户名不能为空");
        Assert.hasText(userVOReq.getUserPwd(), "用户密码不能为空");
        Assert.hasText(userVOReq.getUserPhone(), "用户手机号不能为空");
        //随机生成昵称 DJ+随机6位数
        userVOReq.setNickName("DJ"+CodeTest.getRandom(6));
        userApi.addUser(DozerUtil.map(userVOReq, UserDTO.class));
        return new ResultModel<>().success();
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
     * 获取普通用户密码盐
     *
     * @param userNPE
     * @return
     * @throws Exception
     */
    @PostMapping("getSalt")
    public ResultModel getSalt(String userNPE) throws Exception {
        Assert.hasText(userNPE, "登录信息不能为空");
        UserDTO userDTO = userApi.getSalt1(userNPE);
        return new ResultModel().success(DozerUtil.map(userDTO, UserVOResp.class).getSalt());
    }




}
