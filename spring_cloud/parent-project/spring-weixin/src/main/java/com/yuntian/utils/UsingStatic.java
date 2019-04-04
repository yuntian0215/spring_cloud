package com.yuntian.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
/**
 * <p>处理页面引用静态文件问题</p>
 * 2019年4月3日下午2:44:26
 * @author lvjie
 */
@Configuration
public class UsingStatic extends WebMvcConfigurationSupport{
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
