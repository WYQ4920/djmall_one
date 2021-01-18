package com.dj.mall.auth.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.mall.auth.entity.res.ResourceEntity;
import com.dj.mall.auth.entity.user.UserEntity;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UserMapper extends BaseMapper<UserEntity> {

    /**
     * 获取用户资源信息
     * @param userId 用户ID
     * @return
     * @throws DataAccessException
     */
    List<ResourceEntity> getUserResourceByUserId(Integer userId) throws DataAccessException;
}
