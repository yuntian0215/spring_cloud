package com.yuntian.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * <p>消费者A</p>
 * 2019/3/12/0012 16:58
 *
 * @author lvjie
 */
@Component
@RabbitListener(queues = "fanout.A")
public class FanoutReceiverA {
    @RabbitHandler
    public void process(String msg) {
        System.out.println("FanoutReceiverA  : " + msg);
    }
}
