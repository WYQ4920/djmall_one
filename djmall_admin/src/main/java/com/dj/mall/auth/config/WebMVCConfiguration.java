package com.dj.mall.auth.config;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web配置类
 * @Author WYQ
 * @Date 2021/1/19 10:58
 */

@Configuration
public class WebMVCConfiguration implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
//        InterceptorRegistration loginRegistration = registry.addInterceptor(loginInterceptor);
        // 登录拦截
//        loginRegistration.addPathPatterns("/**");
        //放过拦截路径
//        loginRegistration.excludePathPatterns("/user/toLogin");
//        loginRegistration.excludePathPatterns("/user/login");
//        loginRegistration.excludePathPatterns("/user/toAdd");
//        loginRegistration.excludePathPatterns("/user/add");
//        loginRegistration.excludePathPatterns("/user/checkUserName");
//        loginRegistration.excludePathPatterns("/user/checkUserEmail");
//        loginRegistration.excludePathPatterns("/user/checkUserPhone");
//        loginRegistration.excludePathPatterns("/user/getSalt");
//        loginRegistration.excludePathPatterns("/static/**");
//        loginRegistration.excludePathPatterns("/error");
        // 权限拦截
        InterceptorRegistration authRegistration = registry.addInterceptor(authInterceptor);
        authRegistration.addPathPatterns("/user/toShow");
        authRegistration.addPathPatterns("/res/toShowResZtree");
        authRegistration.addPathPatterns("/auth/role/toShow");
        authRegistration.addPathPatterns("/auth/resource/wyq/toList");
    }
}
