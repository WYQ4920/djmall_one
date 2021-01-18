package com.dj.mall.auth.api.user;

import com.dj.mall.auth.dto.res.ResourceDTO;
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

    /**
     * 获取用户资源信息
     * @param userId 用户ID
     * @return
     * @throws Exception
     */
    List<ResourceDTO> getUserResource(Integer userId)throws Exception;

    /**
     * 用户邮箱查重
     * @param userEmail
     * @return
     * @throws Exception
     */
    boolean checkUserEmail(String userEmail) throws Exception;

    /**
     *  用户手机号查重
     * @param userPhone
     * @return
     * @throws Exception
     */
    boolean checkUserPhone(String userPhone) throws Exception;
}
