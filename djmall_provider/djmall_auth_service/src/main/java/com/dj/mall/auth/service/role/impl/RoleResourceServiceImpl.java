package com.dj.mall.auth.service.role.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.auth.entity.role.RoleResourceEntity;
import com.dj.mall.auth.mapper.role.RoleResourceMapper;
import com.dj.mall.auth.service.role.RoleResourceService;

/**
 * @Author WYQ
 * @Date 2021/1/17 21:31
 */

@Service
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceMapper, RoleResourceEntity> implements RoleResourceService {

}
