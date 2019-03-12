package com.yuntian;

import org.springframework.amqp.core.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * <p>内容</p>
 * 2019/3/12/0012 15:16
 *
 * @author lvjie
 */
@SpringBootApplication
public class RabbitMqRun {
    public static void main(String[] args) {
        SpringApplication.run(RabbitMqRun.class,args);
    }
}
