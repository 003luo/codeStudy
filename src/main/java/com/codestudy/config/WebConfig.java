package com.codestudy.config;

import com.codestudy.interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("addINterceptors:" + loginCheckInterceptor);
        registry.addInterceptor(loginCheckInterceptor)
                .addPathPatterns("/**") // 匹配所有路径;
                // 或者指定特定路径
                //.addPathPatterns("/api/**")
                // 可以排除不需要验证Token的路径
                .excludePathPatterns("/login", "/regedit", "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**","/doc.html/**");
    }
}
