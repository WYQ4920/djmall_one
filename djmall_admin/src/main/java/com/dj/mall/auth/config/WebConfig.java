package com.dj.mall.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private MyInterceptor myInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(myInterceptor);
        interceptorRegistration.addPathPatterns("/admin/**");
        interceptorRegistration.excludePathPatterns("/admin/user/toLogin");
        interceptorRegistration.excludePathPatterns("/admin/user/login");
        interceptorRegistration.excludePathPatterns("/admin/static/**");
    }
}
