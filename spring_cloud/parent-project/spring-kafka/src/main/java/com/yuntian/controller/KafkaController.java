package com.yuntian.controller;

import com.yuntian.producer.FirstProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>内容</p>
 * 2019/4/18/0018 14:07
 *
 * @author lvjie
 */
@RestController
public class KafkaController {
    @Resource
    private FirstProducer firstProducer;
    @RequestMapping("/send")
    public String send(String msg){
        firstProducer.send(msg);
        return "发送消息成功";
    }
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @RequestMapping("/kafkaSendMessage")
    public void sendTest() throws InterruptedException {
        System.out.println("send send data");
        for (int i =0;i<1000;i++) {
            System.out.println("send mock data to kafka  :"+i);

            kafkaTemplate.send("ScrapyLogTopic",
                    "mock data i:  "+i +"  "+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
            Thread.sleep(200);
        }
    }
}
