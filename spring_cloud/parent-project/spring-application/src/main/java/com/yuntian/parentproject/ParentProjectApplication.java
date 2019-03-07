package com.yuntian.parentproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.LinkedHashMap;
import java.util.Map;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.yuntian.parentproject.mapper")//配置mybatis包扫描
public class ParentProjectApplication {

	public static void main(String[] args) {
		SpringApplication springapplication = new SpringApplication(ParentProjectApplication.class);
		Map<String,Object> properties = new LinkedHashMap<>();
		properties.put("server.port",80);//设置启动随机端口
		springapplication.setDefaultProperties(properties);
		springapplication.run(args);
	}

}

