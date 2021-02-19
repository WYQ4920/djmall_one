package com.dj.mall.platform.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author zhengyk
 * @Date 2021/2/5 9:22
 */
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final TokenInterceptor tokenInterceptor;

    public void addInterceptor(InterceptorRegistry registry){

        InterceptorRegistration interceptorRegistration = registry.addInterceptor(tokenInterceptor);
        //  拦截请求
        interceptorRegistration.addPathPatterns("/**");
        //  放过请求
        interceptorRegistration.excludePathPatterns("/user/toLogin");
        interceptorRegistration.excludePathPatterns("/user/login");
        interceptorRegistration.excludePathPatterns("/user/toAdd");
        interceptorRegistration.excludePathPatterns("/user/add");
        interceptorRegistration.excludePathPatterns("/user/getSalt");
        interceptorRegistration.excludePathPatterns("/djmall_platform/toShow");



    }
}
