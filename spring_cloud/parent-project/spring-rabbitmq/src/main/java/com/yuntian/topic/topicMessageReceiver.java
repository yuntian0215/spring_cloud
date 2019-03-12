package com.yuntian.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * <p>topic消费者1 topic.message</p>
 * 2019/3/12/0012 16:47
 *
 * @author lvjie
 */
@Component
@RabbitListener(queues = "topic.message")
public class topicMessageReceiver {
    @RabbitHandler
    public void process(String msg) {
        System.out.println("topicMessageReceiver  : " +msg);
    }
}
