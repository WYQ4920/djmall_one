package com.dj.mall.auth.api;

import com.dj.mall.auth.dto.UserDTO;

import java.util.List;

public interface UserApi {

    UserDTO findUserByNameAndPwd(String userName, String userPwd) throws Exception;

    List<UserDTO> findAll(UserDTO userDTO) throws Exception;

    void addUser(UserDTO userDTO) throws Exception;

    UserDTO findUserById(Integer id) throws Exception;

    void updateUser(UserDTO userDTO) throws Exception;

    boolean checkUserName(String userName) throws Exception;
}
