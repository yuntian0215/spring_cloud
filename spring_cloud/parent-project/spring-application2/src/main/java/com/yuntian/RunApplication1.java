package com.yuntian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * <p>内容</p>
 * 2019/3/1/0001 15:41
 *
 * @author lvjie
 */
@SpringBootApplication
@EnableEurekaClient
public class RunApplication1 {
    public static void main(String[] args) {
        SpringApplication.run(RunApplication1.class,args);
    }
}
