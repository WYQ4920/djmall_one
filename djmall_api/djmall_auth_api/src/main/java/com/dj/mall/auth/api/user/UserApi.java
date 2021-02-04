package com.dj.mall.auth.api.user;

import com.dj.mall.auth.dto.res.ResourceDTO;
import com.dj.mall.auth.dto.role.RoleDTO;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.common.base.BusinessException;

import java.util.List;

public interface UserApi {

    /**
     * 用户登录
     *
     * @param userName
     * @param userPwd
     * @return
     * @throws BusinessException
     */
    UserDTO findUserByNameAndPwd(String userName, String userPwd) throws Exception, BusinessException;

    /**
     * 用户展示
     *
     * @param userDTO
     * @return
     */
    List<UserDTO> findUserAll(UserDTO userDTO) throws Exception;

    /**
     * 新增用户
     *
     * @param userDTO
     * @return
     * @throws Exception
     */
    void addUser(UserDTO userDTO) throws BusinessException, Exception;

    /**
     * 通过Id 查用户
     *
     * @param id
     * @return
     * @throws Exception
     */
    UserDTO findUserById(Integer id) throws Exception;

    /**
     * 修改用户
     *
     * @param userDTO
     * @throws BusinessException
     */
    void updateUser(UserDTO userDTO) throws BusinessException;

    /**
     * 用户名查重
     *
     * @param userName
     * @return
     * @throws Exception
     */
    boolean checkUserName(String userName) throws Exception;

    /**
     * 获取用户资源信息
     *
     * @param userId 用户ID
     * @return
     * @throws Exception
     */
    List<ResourceDTO> getUserResource(Integer userId) throws Exception;

    /**
     * 用户邮箱查重
     *
     * @param userEmail
     * @return
     * @throws Exception
     */
    boolean checkUserEmail(String userEmail) throws Exception;

    /**
     * 用户手机号查重
     *
     * @param userPhone
     * @return
     * @throws Exception
     */
    boolean checkUserPhone(String userPhone) throws Exception;

    /**
     * 获取用户密码盐
     *
     * @param userName
     * @return
     * @throws Exception
     */
    UserDTO getSalt(String userName) throws Exception;

    /**
     * 用户删除
     *
     * @param ids
     * @throws Exception
     */
    void del(Integer[] ids) throws Exception;

    /**
     * 用户授予角色
     *
     * @param userId
     * @param roleId
     * @throws Exception
     */
    void giveRole(Integer userId, Integer roleId) throws Exception;

    /**
     * 通过用户id查找角色
     *
     * @param userId
     * @return
     * @throws Exception
     */
    Integer findRoleByUserId(Integer userId) throws Exception;

    /**
     * 所有角色集合
     *
     * @return
     * @throws Exception
     */
    List<RoleDTO> findAllRole() throws Exception;

    /**
     * 重置密码
     * @param admin 登录的管理员
     * @param id 重置密码的用户id
     * @return
     * @throws Exception
     */
    boolean resetPwd(UserDTO admin, Integer id) throws Exception;

    /**
     * 忘记密码修改
     * @param userDTO
     */
    void updatePwd(UserDTO userDTO);

    /**
     * 买家登录
     * @param userNPE
     * @param userPwd
     * @return
     */
    UserDTO findUserByNPEAndPwd(String userNPE, String userPwd)throws Exception;

    /**
     * 买家登录获得用户盐
     * @param userNEP
     * @return
     */
    UserDTO getSalt1(String userNPE);
}
