package com.yuntian.hello;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * <p>消费者2</p>
 * 2019/3/12/0012 15:23
 *
 * @author lvjie
 */
@Component
@RabbitListener(queues = "hello")//指定监控哪个队列
public class HelloReceiver2 {
    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver2  : " + hello);
    }
}
