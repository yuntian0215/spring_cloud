package com.yuntian;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * <p>内容</p>
 * 2019/3/1/0001 15:19
 *
 * @author lvjie
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.yuntian.mapper")
public class RunApplication {
    public static void main(String[] args) {
        SpringApplication.run(RunApplication.class,args);
    }
}
