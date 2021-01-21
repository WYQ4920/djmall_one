package com.dj.mall.auth.config;

import com.dj.mall.auth.dto.res.ResourceDTO;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.common.constant.UserConstant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义Realm
 */
@Component
public class ShiroRealm extends AuthorizingRealm {

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        UserDTO user = (UserDTO) SecurityUtils.getSubject().getSession().getAttribute(UserConstant.USER_SESSION);
        List<ResourceDTO> userResList = user.getResourceList();
        //创建简单授权信息
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
        for (ResourceDTO list : userResList) {
            simpleAuthorInfo.addStringPermission(list.getResourceCode());
        }
        return  simpleAuthorInfo;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 得到用户名
        String userName = (String) authenticationToken.getPrincipal();
        // 得到密码
        String userPwd = new String((char[]) authenticationToken.getCredentials());
        //认证成功
        return new SimpleAuthenticationInfo(userName, userPwd, getName());
    }

}
