package com.yuntian.callback;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * <p>内容</p>
 * 2019/3/12/0012 17:09
 *
 * @author lvjie
 */
@Component
public class CallBackSender implements  RabbitTemplate.ConfirmCallback{
    @Autowired
    private RabbitTemplate rabbitTemplatenew;
    public void send() {

        rabbitTemplatenew.setConfirmCallback(this);
        String msg="callbackSender : i am callback sender";
        System.out.println(msg );
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        System.out.println("callbackSender UUID: " + correlationData.getId());
        this.rabbitTemplatenew.convertAndSend("exchange", "topic.messages", msg, correlationData);
    }
    //可以看出callbackSender发出的UUID，收到了回应，又传回来了
    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        System.out.println("callbakck confirm: " + correlationData.getId());
    }
}
