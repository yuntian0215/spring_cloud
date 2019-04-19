package com.yuntian.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * <p>生产者</p>
 * 2019/4/18/0018 13:47
 *
 * @author lvjie
 */
@Component
public class FirstProducer {
    @Autowired
    private KafkaTemplate kafkaTemplate;
    /***
    *@Description  推送消息
    *@Param [msg]
    *@Return void
    *@Author lvjie
    *@Date 2019/4/18/0018 13:51
    */
    public void send(String msg){
        System.out.println("生产者发送消息");
        kafkaTemplate.send("ScrapyLogTopic","123",msg);
    }

}
