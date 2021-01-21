package com.dj.mall.auth.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.mall.auth.bo.resource.ResourceBO;
import com.dj.mall.auth.bo.user.UserBO;
import com.dj.mall.auth.entity.user.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UserMapper extends BaseMapper<UserEntity> {

    /**
     * 获取用户资源信息
     * @param userId 用户ID
     * @return
     * @throws DataAccessException
     */
    List<ResourceBO> getUserResourceByUserId(Integer userId) throws DataAccessException;


    /**
     *  用户展示
     * @param userEntity
     * @param userId
     * @return
     * @throws DataAccessException
     */
    List<UserBO> findUserAll(@Param("userEntity") UserEntity userEntity) throws DataAccessException;
}
