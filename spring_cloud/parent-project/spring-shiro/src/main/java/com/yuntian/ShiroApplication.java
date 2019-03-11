package com.yuntian;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>内容</p>
 * 2019/3/8/0008 14:08
 *
 * @author lvjie
 */
@SpringBootApplication
@MapperScan("com.yuntian.mapper")
public class ShiroApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShiroApplication.class,args);
    }
}
