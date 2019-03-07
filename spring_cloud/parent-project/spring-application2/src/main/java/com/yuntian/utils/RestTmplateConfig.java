package com.yuntian.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * <p>内容</p>
 * 2019/3/1/0001 15:43
 *
 * @author lvjie
 */
@Configuration
public class RestTmplateConfig {
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
