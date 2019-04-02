package com.twx.page.Interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginConfig implements WebMvcConfigurer {

    public void addInterceptors(InterceptorRegistry registry) {
       /*registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/regist").excludePathPatterns("/login").excludePathPatterns("/guanlilogin");*/
    }
}
