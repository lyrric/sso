package com.demo.sso.client.config;

import com.demo.sso.client.interceptor.WebInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

/**
 * Created on 2018/9/19.
 *
 * @author wangxiaodong
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private WebInterceptor webInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webInterceptor).addPathPatterns("/**");
    }
}
