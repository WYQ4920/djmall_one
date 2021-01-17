package com.dj.mall.auth.api.user;

import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.common.base.BusinessException;

import java.util.List;

public interface UserApi {

    UserDTO findUserByNameAndPwd(String userName, String userPwd) throws BusinessException;

    List<UserDTO> findAll(UserDTO userDTO) throws Exception;

    void addUser(UserDTO userDTO) throws Exception;

    UserDTO findUserById(Integer id) throws Exception;

    void updateUser(UserDTO userDTO) throws BusinessException;

    boolean checkUserName(String userName) throws Exception;
}
