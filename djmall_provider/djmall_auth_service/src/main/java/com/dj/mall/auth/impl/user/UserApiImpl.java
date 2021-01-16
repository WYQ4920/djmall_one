package com.dj.mall.auth.impl.user;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.auth.api.user.UserApi;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.auth.entity.user.UserEntity;
import com.dj.mall.auth.mapper.user.UserMapper;
import com.dj.mall.common.base.BusinessException;
import com.dj.mall.common.util.DozerUtil;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserApiImpl extends ServiceImpl<UserMapper, UserEntity> implements UserApi {

    /**
     * 登录
     *
     * @param userName
     * @param userPwd
     * @return
     */
    @Override
    public UserDTO findUserByNameAndPwd(String userName, String userPwd) throws Exception {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName).eq("user_pwd", userPwd);
        UserEntity userEntity = this.getOne(queryWrapper);
        UserDTO userDTO = DozerUtil.map(userEntity, UserDTO.class);
        if (userDTO == null) {
            throw new BusinessException("用户名或密码不正确");
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
    public List<UserDTO> findAll(UserDTO userDTO) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if (!StringUtils.isEmpty(userDTO.getUserName())) {
            queryWrapper.like("user_name", userDTO.getUserName());
        }
        List<UserEntity> list = this.list(queryWrapper);
        return DozerUtil.mapList(list, UserDTO.class);
    }

    /**
     * 新增用户
     *
     * @param userDTO
     * @throws Exception
     */
    @Override
    public void addUser(UserDTO userDTO) throws Exception {
        UserEntity userEntity = DozerUtil.map(userDTO, UserEntity.class);
        this.save(userEntity);
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
     * @throws Exception
     */
    @Override
    public void updateUser(UserDTO userDTO) throws Exception {
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


}
