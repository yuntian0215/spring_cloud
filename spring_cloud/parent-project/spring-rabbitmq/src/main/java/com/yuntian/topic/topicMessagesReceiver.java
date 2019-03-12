package com.yuntian.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * <p>消费者２（topic.messages）</p>
 * 2019/3/12/0012 16:48
 *
 * @author lvjie
 */
@Component
@RabbitListener(queues = "topic.messages")
public class topicMessagesReceiver {
    @RabbitHandler
    public void process(String msg) {
        System.out.println("topicMessagesReceiver  : " +msg);
    }
}
