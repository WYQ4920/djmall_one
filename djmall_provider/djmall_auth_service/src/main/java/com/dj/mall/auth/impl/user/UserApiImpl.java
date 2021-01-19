package com.dj.mall.auth.impl.user;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.auth.api.user.UserApi;
import com.dj.mall.auth.bo.user.UserBO;
import com.dj.mall.auth.dto.res.ResourceDTO;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.auth.entity.res.ResourceEntity;
import com.dj.mall.auth.entity.user.UserEntity;
import com.dj.mall.auth.entity.user.UserRoleEntity;
import com.dj.mall.auth.mapper.user.UserMapper;
import com.dj.mall.auth.service.user.UserRoleService;
import com.dj.mall.common.base.BusinessException;
import com.dj.mall.common.util.DozerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserApiImpl extends ServiceImpl<UserMapper, UserEntity> implements UserApi {

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 用户登录
     *
     * @param userName
     * @param userPwd
     * @return
     * @throws BusinessException
     */
    @Override
    public UserDTO findUserByNameAndPwd(String userName, String userPwd) throws BusinessException {
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
        List<UserBO> list = getBaseMapper().findUserAll(DozerUtil.map(userDTO,UserEntity.class));
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
    public void addUser(UserDTO userDTO) throws Exception {
        // 用户表新增
        UserEntity userEntity = DozerUtil.map(userDTO, UserEntity.class);
        super.save(userEntity);
        UserRoleEntity userRoleEntity = new UserRoleEntity()
                .setUserId(userEntity.getId()).setRoleId(userDTO.getRoleId());
        userRoleService.save(userRoleEntity);
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
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userDTO.getUserName());
        UserEntity userEntity = this.getOne(queryWrapper);
        if (userEntity != null && !userEntity.getId().equals(userDTO.getId())) {
            throw new BusinessException("用户名重复");
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
        List<ResourceEntity> resourceEntities = getBaseMapper().getUserResourceByUserId(userId);
        return DozerUtil.mapList(resourceEntities, ResourceDTO.class);
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
     * @param userDTO
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void del(UserDTO userDTO) throws Exception {
        //删除用户id
        this.removeById(userDTO.getId());
        QueryWrapper<UserRoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userDTO.getId());
        this.userRoleService.remove(queryWrapper);
    }

    /**
     * 用户授予角色
     * @param userId
     * @param roleId
     * @throws Exception
     */
    @Override
    public void giveRole(Integer userId,Integer roleId) throws Exception {
        QueryWrapper<UserRoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        UserRoleEntity userRoleEntity1 = userRoleService.getOne(queryWrapper);
        if (userRoleEntity1==null){
            UserRoleEntity userRoleEntity=new UserRoleEntity()
                    .setUserId(userId).setRoleId(roleId);
            userRoleService.save(userRoleEntity);
        }else {
            userRoleEntity1.setRoleId(roleId);
            userRoleService.update(userRoleEntity1,queryWrapper);
        }



    }


}
