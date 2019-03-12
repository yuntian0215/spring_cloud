package com.yuntian.hello;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * <p>生产者</p>
 * 2019/3/12/0012 15:21
 *
 * @author lvjie
 */
@Component
public class HelloSender1 {
    @Autowired
    private AmqpTemplate amqpTemplate;
    public void send(String msg) {
        String sendMsg = msg + new Date();
        System.out.println("Sender1 : " + sendMsg);
        //指定往哪个队列发送消息
        this.amqpTemplate.convertAndSend("hello", sendMsg);
    }
}
