package com.dj.mall.auth.service.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.auth.service.user.UserRoleService;
import com.dj.mall.auth.entity.user.UserRoleEntity;
import com.dj.mall.auth.mapper.user.UserRoleMapper;
import org.springframework.stereotype.Service;

/**
 * @Author WYQ
 * @Date 2021/1/18 1:43
 */

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleEntity> implements UserRoleService {
}
