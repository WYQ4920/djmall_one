package com.dj.mall.auth.config;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.api.user.UserApi;
import com.dj.mall.auth.dto.res.ResourceDTO;
import com.dj.mall.auth.dto.user.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 权限拦截
 * @Author WYQ
 * @Date 2021/1/19 11:14
 */

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Reference
    private UserApi userApi;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 当前访问URL
        String currUrl = request.getRequestURI();
        String ctx = request.getContextPath();
        // 用户授权URL
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        List<ResourceDTO> userResourceList = userApi.getUserResource(user.getId());
        for (ResourceDTO res : userResourceList) {
            if ((ctx + res.getUrl()).equals(currUrl)) {
                return true;
            }
        }
        // 未授权访问返回403
        response.sendRedirect(ctx +"/403.jsp");
        return false;
    }
}
