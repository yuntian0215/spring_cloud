package com.yuntian.consumer;

import java.util.Optional;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;



/**
 * <p>测试接收对象</p>
 * 2019年4月22日上午8:35:33
 * @author lvjie
 */
@Component
public class UserConsumer {
	//测试接收对象
	@KafkaListener(topics={"ScrapyLogTopic"})
	public void listenUser(ConsumerRecord<?, ?> record){
		Optional<?> kafkaMessage = Optional.ofNullable(record.value());
		if (kafkaMessage.isPresent()) {

            Object message = kafkaMessage.get();
            System.out.println("---->"+message);
            String str = kafkaMessage.get().toString();
            JSONObject obj = JSONObject.parseObject(str);
            System.out.println(obj.get("id")+"--->"+obj.get("name")+"--->"+obj.get("address"));
        }
	}
}
