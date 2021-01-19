package com.dj.mall.auth.config;

import com.dj.mall.auth.dto.user.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录访问拦截
 * @Author WYQ
 * @Date 2021/1/19 9:58
 */

@Component
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 访问前拦截
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(request.getRequestURL());
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath()+"/user/toLogin");
            return false;
        }
        return true;
    }
}
