package com.dj.mall.auth.impl.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.auth.api.role.RoleApi;
import com.dj.mall.auth.api.user.UserApi;
import com.dj.mall.auth.bo.resource.ResourceBO;
import com.dj.mall.auth.bo.user.UserBO;
import com.dj.mall.auth.dto.res.ResourceDTO;
import com.dj.mall.auth.dto.role.RoleDTO;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.auth.entity.user.UserEntity;
import com.dj.mall.auth.entity.user.UserRoleEntity;
import com.dj.mall.auth.mapper.user.UserMapper;
import com.dj.mall.auth.service.user.UserRoleService;
import com.dj.mall.cmpt.EMailApi;
import com.dj.mall.common.base.BusinessException;
import com.dj.mall.common.constant.UserConstant;
import com.dj.mall.common.util.DozerUtil;
import com.dj.mall.common.util.PasswordSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Service
public class UserApiImpl extends ServiceImpl<UserMapper, UserEntity> implements UserApi {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleApi roleApi;

    @Reference
    private EMailApi eMailApi;

    @Value("${system.email.restPwd}")
    private String resetPwdText;

    /**
     * 用户登录
     *
     * @param userName
     * @param userPwd
     * @return
     * @throws BusinessException
     */
    @Override
    public UserDTO findUserByNameAndPwd(String userName, String userPwd) throws Exception, BusinessException {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        UserEntity userEntity = this.getOne(queryWrapper);
        UserDTO userDTO = DozerUtil.map(userEntity, UserDTO.class);
        if (userDTO == null) {
            throw new BusinessException("用户名不存在");
        }
        if (!userDTO.getUserPwd().equals(userPwd)) {
            throw new BusinessException("密码不正确");
        }
        if (userDTO.getUserStatus().equals(UserConstant.USER_STATUS_NOT_ACTIVE)){
            throw new BusinessException("还未激活，请去邮箱激活！");
        }
        //  List<ResourceDTO> userResource = this.getUserResource(userDTO.getId());
        //  userDTO.setResourceList(userResource);
        // 获取登录用户的角色
        QueryWrapper<UserRoleEntity> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("user_id", userDTO.getId());
        UserRoleEntity userRoleEntity = userRoleService.getOne(queryWrapper1);
        if (userRoleEntity == null) {
            throw new BusinessException("用户无角色，请联系管理员！");
        }
        userDTO.setRoleId(userRoleEntity.getRoleId());
        return userDTO;
    }

    /**
     * 用户展示
     *
     * @param userDTO
     * @return
     */
    @Override
    public List<UserDTO> findUserAll(UserDTO userDTO) {
        List<UserBO> list = getBaseMapper().findUserAll(DozerUtil.map(userDTO, UserBO.class));
        return DozerUtil.mapList(list, UserDTO.class);
    }

    /**
     * 新增用户
     *
     * @param userDTO
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUser(UserDTO userDTO) throws BusinessException,Exception {
        if (userDTO.getUserName().equals(userDTO.getNickName())) {
            throw new BusinessException("用户名和昵称一致");
        }
        // 用户表新增
        UserEntity userEntity = DozerUtil.map(userDTO, UserEntity.class);
        // 默认为激活状态
        userEntity.setUserStatus(UserConstant.USER_STATUS_ACTIVE);
        // 商户为未激活状态
        if (userDTO.getRoleId().equals(UserConstant.MERCHANT_ROLE_ID)){
            userEntity.setUserStatus(UserConstant.USER_STATUS_NOT_ACTIVE);
        }
        super.save(userEntity);
        // 用户角色关联
        UserRoleEntity userRoleEntity = new UserRoleEntity()
                .setUserId(userEntity.getId()).setRoleId(userDTO.getRoleId());
        userRoleService.save(userRoleEntity);
        // 注册的用户是商户--发邮件
        if (userDTO.getRoleId().equals(UserConstant.MERCHANT_ROLE_ID)){

            String emailText="<h2>恭喜注册成功，<a href='http://localhost:8081/admin/user/active?id="
                    +userEntity.getId()+"'>点我激活</a></h2>";
            // 发激活邮件
            //eMailApi.sendMailHTML(userDTO.getUserEmail(),"激活邮件",emailText);
            System.out.println(emailText);
        }
    }

    /**
     * 通过Id 查用户
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public UserDTO findUserById(Integer id) throws Exception {
        UserEntity userEntity = this.getById(id);
        return DozerUtil.map(userEntity, UserDTO.class);
    }

    /**
     * 修改用户
     *
     * @param userDTO
     * @throws BusinessException
     */
    @Override
    public void updateUser(UserDTO userDTO) throws BusinessException {
        //用户名查重
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userDTO.getUserName());
        UserEntity userEntity = this.getOne(queryWrapper);
        if (userEntity != null && !userEntity.getId().equals(userDTO.getId())) {
            throw new BusinessException("用户名重复");
        }
        //手机号查重
        QueryWrapper<UserEntity> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("user_phone", userDTO.getUserPhone());
        UserEntity userEntity1 = this.getOne(queryWrapper1);
        if (userEntity1 != null && !userEntity1.getUserPhone().equals(userDTO.getUserPhone())) {
            throw new BusinessException("手机号已经注册");
        }
        //邮箱查重
        QueryWrapper<UserEntity> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("user_email", userDTO.getUserEmail());
        UserEntity userEntity2 = this.getOne(queryWrapper2);
        if (userEntity2 != null && !userEntity2.getUserEmail().equals(userDTO.getUserEmail())) {
            throw new BusinessException("邮箱已经注册");
        }
        this.updateById(DozerUtil.map(userDTO, UserEntity.class));
    }

    /**
     * 用户名查重
     *
     * @param userName
     * @return
     * @throws Exception
     */
    @Override
    public boolean checkUserName(String userName) throws Exception {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        UserEntity userEntity = this.getOne(queryWrapper);
        if (userEntity != null) {
            return false;
        }
        return true;
    }

    /**
     * 获取用户资源信息
     *
     * @param userId 用户ID
     * @return
     * @throws Exception
     */
    @Override
    public List<ResourceDTO> getUserResource(Integer userId) throws Exception {
        List<ResourceBO> resourceBOS = getBaseMapper().getUserResourceByUserId(userId);
        return DozerUtil.mapList(resourceBOS, ResourceDTO.class);
    }

    /**
     * 用户邮箱查重
     *
     * @param userEmail
     * @return
     * @throws Exception
     */
    @Override
    public boolean checkUserEmail(String userEmail) throws Exception {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_email", userEmail);
        UserEntity userEntity = this.getOne(queryWrapper);
        if (userEntity != null) {
            return false;
        }
        return true;
    }

    /**
     * 用户手机号查重
     *
     * @param userPhone
     * @return
     * @throws Exception
     */
    @Override
    public boolean checkUserPhone(String userPhone) throws Exception {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_phone", userPhone);
        UserEntity userEntity = this.getOne(queryWrapper);
        if (userEntity != null) {
            return false;
        }
        return true;
    }


    /**
     * 获取用户密码盐
     *
     * @param userName
     * @return
     * @throws Exception
     */
    @Override
    public UserDTO getSalt(String userName) throws Exception {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        UserEntity userEntity = this.getOne(queryWrapper);
        return DozerUtil.map(userEntity, UserDTO.class);
    }

    /**
     * 删除用户
     *
     * @param ids
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void del(Integer[] ids) throws Exception {
        //删除用户id
        this.removeByIds(Arrays.asList(ids));
        QueryWrapper<UserRoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("user_id", Arrays.asList(ids));
        this.userRoleService.remove(queryWrapper);
    }

    /**
     * 用户授予角色
     *
     * @param userId
     * @param roleId
     * @throws Exception
     */
    @Override
    public void giveRole(Integer userId, Integer roleId) throws Exception {
        QueryWrapper<UserRoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        UserRoleEntity userRoleEntity1 = userRoleService.getOne(queryWrapper);
        if (userRoleEntity1 == null) {
            UserRoleEntity userRoleEntity = new UserRoleEntity()
                    .setUserId(userId).setRoleId(roleId);
            userRoleService.save(userRoleEntity);
        } else {
            userRoleEntity1.setRoleId(roleId);
            userRoleService.update(userRoleEntity1, queryWrapper);
        }
    }

    /**
     * 通过用户id查找角色
     *
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public Integer findRoleByUserId(Integer userId) throws Exception {
        QueryWrapper<UserRoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        UserRoleEntity userRole = userRoleService.getOne(queryWrapper);
        return userRole.getRoleId();
    }

    /**
     * 所有角色集合
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<RoleDTO> findAllRole() throws Exception {
        List<RoleDTO> roleList = roleApi.getRoleList();
        return roleList;
    }

    /**
     * 重置密码
     *
     * @param admin 登录的管理员
     * @param id    重置密码的用户id
     * @return
     * @throws Exception
     */
    @Override
    public boolean resetPwd(UserDTO admin, Integer id) throws Exception {
        // 随机6位数密码
        String random = PasswordSecurityUtil.generateRandom(6);
        String salt = PasswordSecurityUtil.generateSalt();
        // 生成新的密码
        String newPwd = PasswordSecurityUtil.generatePassword(PasswordSecurityUtil.enCode32(random), salt);
        // 修改密码
        UserEntity userEntity = new UserEntity().setId(id).setUserPwd(newPwd).setSalt(salt);
        super.updateById(userEntity);
        // 通过id查要修改的用户
        UserEntity userEntity1 = super.getById(id);

        LocalDateTime now = LocalDateTime.now();
        // 日期转换
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String emailText = String.format(resetPwdText, userEntity1.getNickName(),admin.getNickName(), dateTimeFormatter.format(now),random);
        // 发送邮件
        //eMailApi.sendMailHTML(userEntity1.getUserEmail(),"重置密码邮件",emailText);
        System.out.println(emailText);
        return true;
    }

}
