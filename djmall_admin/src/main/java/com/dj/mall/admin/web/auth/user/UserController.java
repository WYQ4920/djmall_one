package com.dj.mall.admin.web.auth.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.dj.mall.admin.vo.user.UserVOReq;
import com.dj.mall.admin.vo.user.UserVOResp;
import com.dj.mall.auth.api.res.ResourceApi;
import com.dj.mall.auth.api.user.UserApi;
import com.dj.mall.auth.dto.res.ResourceDTO;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.cmpt.PhoneApi;
import com.dj.mall.common.base.BusinessException;
import com.dj.mall.common.base.ResultModel;
import com.dj.mall.common.constant.UserConstant;
import com.dj.mall.common.util.CodeTest;
import com.dj.mall.common.util.DozerUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RequestMapping("/user/")
@RestController
public class UserController {

    @Reference
    private UserApi userApi;

    @Reference
    private ResourceApi resourceApi;

    @Autowired
    private RedisTemplate redisTemplate;

    @Reference
    private PhoneApi phoneApi;

    /**
     * 登录
     *
     * @param userName
     * @param userPwd
     * @return
     */
    @PostMapping("login")
    public ResultModel<Object> login(String userName, String userPwd, HttpSession session) throws Exception, BusinessException {
        Assert.hasText(userName, "用户名不能为空");
        Assert.hasText(userPwd, "密码不能为空");
        UserDTO userDTO = userApi.findUserByNameAndPwd(userName, userPwd);
        List<ResourceDTO> resList = userApi.getUserResource(userDTO.getId());
        userDTO.setResourceList(resList);
        session.setAttribute(UserConstant.USER_SESSION, userDTO);
        //shiro认证
        //得到主体
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, userPwd);
        //发起请求认证
        subject.login(token);
        return new ResultModel<>().success();
    }

    /**
     * 用户展示
     *
     * @param userVOReq
     * @return
     */
    @PostMapping("list")
    @RequiresPermissions("USER_MANAGER")
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
    public ResultModel<Object> add(UserVOReq userVOReq) throws BusinessException, Exception {
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
    @RequiresPermissions("USER_UPDATE_BTN")
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
     *
     * @param ids
     * @return
     * @throws Exception
     */
    @PostMapping("del")
    @RequiresPermissions("USER_DEL_BTN")
    public ResultModel del(@RequestParam("ids[]") Integer[] ids) throws Exception {
        userApi.del(ids);
        return new ResultModel().success();
    }

    /**
     * 用户授予角色
     *
     * @param userId
     * @param roleId
     * @return
     * @throws Exception
     */
    @PostMapping("giveRole")
    @RequiresPermissions("USER_ADD_ROLE_BTN")
    public ResultModel giveRole(Integer userId, Integer roleId) throws Exception {
        userApi.giveRole(userId, roleId);
        return new ResultModel().success();
    }

    /**
     * 重置密码
     * @param admin 登录的管理员
     * @param id 重置密码的用户id
     * @return
     * @throws Exception
     */
    @PostMapping("resetPwd")
    @RequiresPermissions("USER_RESET_PWD_BTN")
    public ResultModel resetPwd(@SessionAttribute(UserConstant.USER_SESSION) UserDTO admin, Integer id) throws Exception {
        userApi.resetPwd(admin, id);
        return new ResultModel().success();
    }

    /**
     * 忘记密码
     */
    @PostMapping("sendSms")
    public ResultModel sendSms(String verifyCode, String userPhone, @SessionAttribute(UserConstant.SESSION_VERIFY_CODE) String checkVerifyCode) throws Exception {
        //验证图形验证码有效性，忽略大小写
        Assert.hasText(verifyCode, "图形验证码不能为空");
        Assert.state(verifyCode.toUpperCase().equals(checkVerifyCode.toUpperCase()),"图形验证码输入错误");
        Assert.hasText(userPhone, "手机号不能为空");

        // 验证手机号是否存在 待写
        boolean b = userApi.checkUserPhone(userPhone);
        if(b){
            return new ResultModel().error("手机号未注册");
        }
        //发送短信
        HashOperations hashOperations = redisTemplate.opsForHash();
        String key = "SMS_"+ LocalDate.now();
        String field = "FORGET_PWD_"+userPhone;
        JSONObject obj = (JSONObject) hashOperations.get(key, field);
        // 第一次
        String smsCode =  CodeTest.getCode(4);
        System.out.println(smsCode);
        if(null == obj){
            obj = new JSONObject();
            obj.put("code",smsCode);

            //当前时间加60s
            obj.put("timeout", System.currentTimeMillis()+60000);
            obj.put("count",1);
            //  更新
            hashOperations.put(key, field, obj);
            //  设置超时时间
            redisTemplate.expire(key, 1, TimeUnit.DAYS);
        }else {
            if(obj.getInteger("count")>= 5){
                return  new ResultModel().error("上限5");
            }else {
                // 判断上个验证码是否过期 待写
                obj.put("code", smsCode);

                //当前时间加60s
                obj.put("timeout", System.currentTimeMillis()+60000);
                obj.put("count",obj.getInteger("count")+1);
                //  更新
                hashOperations.put(key, field, obj);
            }
        }
        //  发送短信
//        phoneApi.sendSms(userPhone,smsCode);
        return new ResultModel().success();
    }


    /**
     * 忘记密码
     */
    @PostMapping("forgetPwd")
    public ResultModel forgetPwd(String salt, String userPhone, String smsCode, String pwd , String pwd2) throws Exception {
        //    校验参数
        Assert.hasText(userPhone, "手机号不能为空");
        Assert.hasText(smsCode, "短信验证码不能为空");
        // 手机验证码的有效性
        HashOperations hashOperations = redisTemplate.opsForHash();
        String key = "SMS_" + LocalDate.now();
        String field  = "FORGET_PWD_" +userPhone;
        //   从rdeis中获取
        JSONObject obj = (JSONObject) hashOperations.get(key, field);
        //   是否已发送短信
        if(null == obj){
            return new ResultModel().error("请发送短信验证码");
        }
        //  是否超时
        long timeout = obj.getLong("timeout");
        if(System.currentTimeMillis() > timeout){
            return new ResultModel().error("短信验证码已超时");
        }
        //  手就验证码是否一致
        Assert.state(smsCode.equals(obj.getString("code")),"验证码输入有误");

        //  密码是否一致
        Assert.state(pwd.equals(pwd2),"密码不一致");
        //  重置密码
        UserDTO userDTO = new UserDTO();
        userDTO.setUserPhone(userPhone);
        userDTO.setUserPwd(pwd);
        userDTO.setSalt(salt);
        userApi.updatePwd(userDTO);
        return new ResultModel().success();
    }


}
