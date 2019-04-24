package com.yuntian.producer;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yuntian.model.User;
/**
 * <p>测试传输对象</p>
 * 2019年4月22日上午8:31:22
 * @author lvjie
 */
@Component
public class UserProducer {
	@Resource
	private KafkaTemplate<String, String> kafkaTemplate;
	private static Gson gson = new GsonBuilder().create();
	//获取配置文件当中的topic
	@Value("${kafka.crawler.topic}")
	private String topic;
	//测试传输对象
	public void sendUser(User user){
		ListenableFuture<?> send = kafkaTemplate.send(topic, gson.toJson(user));
		send.addCallback(result -> System.out.println("发送成功！"), 
				err -> System.out.println("发送失败！"));
	}
	
}
