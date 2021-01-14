package com.dj.mall.auth.api;

import com.dj.mall.auth.dto.UserDTO;

public interface UserApi {

    UserDTO findUserByNameAndPwd(String userName, String userPwd);
}
