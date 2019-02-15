package com.hww.es.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class LoginConfigurerAdapter extends WebMvcConfigurerAdapter {

	@Bean
	public LoginInterceptor LoginInterceptor() {
		return new LoginInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
//		registry.addInterceptor(LoginInterceptor()).addPathPatterns("/search/**").excludePathPatterns("/login");
	}

}
