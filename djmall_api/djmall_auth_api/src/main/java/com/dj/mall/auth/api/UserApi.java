package com.dj.mall.auth.api;

import com.dj.mall.auth.dto.UserDTO;

import java.util.List;

public interface UserApi {

    UserDTO findUserByNameAndPwd(String userName, String userPwd) throws Exception;

    List<UserDTO> findAll(UserDTO userDTO);
}
