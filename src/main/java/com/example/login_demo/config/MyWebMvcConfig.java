package com.example.login_demo.config;

import com.example.login_demo.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
    @Resource
    private MyInterceptor myInterceptor;
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
////        WebMvcConfigurer.super.addInterceptors(registry);
//        registry.addInterceptor(myInterceptor);
//    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        将StringHttpMessageConverter从解析器列表中剔除即可。
        converters.removeIf(e -> e.getClass().isAssignableFrom(StringHttpMessageConverter.class));
    }
}
