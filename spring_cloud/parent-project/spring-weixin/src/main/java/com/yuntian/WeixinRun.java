package com.yuntian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * <p>微信启动主类</p>
 * 2019/4/4/0004 13:53
 *
 * @author lvjie
 */
@SpringBootApplication
@EnableScheduling
public class WeixinRun {
    public static void main(String[] args) {
        SpringApplication.run(WeixinRun.class,args);
    }
}
