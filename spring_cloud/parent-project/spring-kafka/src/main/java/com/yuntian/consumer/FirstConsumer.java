package com.yuntian.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * <p>消费者</p>
 * 2019/4/18/0018 13:54
 *
 * @author lvjie
 */
@Component
public class FirstConsumer {
    @KafkaListener(topics = {"ScrapyLogTopic"})
    public void listen(String record){
        System.out.print("测试消费者收到消息："+record);
    }
}
