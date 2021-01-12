package com.dj.mall.auth.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.auth.api.UserApi;
import com.dj.mall.auth.dto.UserDTO;
import com.dj.mall.auth.entity.UserEntity;
import com.dj.mall.auth.mapper.UserMapper;

import com.dj.mall.common.util.DozerUtil;

@Service
public class UserApiImpl extends ServiceImpl<UserMapper, UserEntity> implements UserApi {
    @Override
    public UserDTO getUser(Integer userId) {

        return DozerUtil.map(super.getById(userId), UserDTO.class);
    }
}
