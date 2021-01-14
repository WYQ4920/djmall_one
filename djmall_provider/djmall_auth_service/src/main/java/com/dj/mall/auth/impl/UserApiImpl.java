package com.dj.mall.auth.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.auth.api.UserApi;
import com.dj.mall.auth.dto.UserDTO;
import com.dj.mall.auth.entity.UserEntity;
import com.dj.mall.auth.mapper.UserMapper;

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
        return DozerUtil.map(userEntity, UserDTO.class);
    }

    /**
     * 用户展示
     * @param userDTO
     * @return
     */
    @Override
    public List<UserDTO> findAll(UserDTO userDTO) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if (!StringUtils.isEmpty(userDTO.getUserName())){
            queryWrapper.like("user_name",userDTO.getUserName());
        }
        List<UserEntity> list = this.list(queryWrapper);
        return DozerUtil.mapList(list,UserDTO.class);
    }
}
