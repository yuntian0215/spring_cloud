package com.yuntian.topic;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** topic队列消费者
 * <p>topic 是RabbitMQ中最灵活的一种方式，可以根据binding_key自由的绑定不同的队列</p>
 * 首先对topic规则配置，这里使用两个队列来测试（也就是在QueueConfig类中创建和绑定的topic.message和topic.messages两个队列），
 * 其中topic.message的bindting_key为“topic.message”，topic.messages的binding_key为“topic.#”；
 * 2019/3/12/0012 16:43
 *
 * @author lvjie
 */
@Component
public class TopicSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;
    /**
     * topic exchange类型rabbitmq测试
     * 结果可知：sender1发送的消息,routing_key是“topic.message”，所以exchange里面的绑定的binding_key是“topic.message”，topic.＃都符合路由规则;所以sender1
     * 发送的消息，两个队列都能接收到；
     *
     * sender2发送的消息，routing_key是“topic.messages”，所以exchange里面的绑定的binding_key只有topic.＃都符合路由规则;所以sender2发送的消息只有队列
     * topic.messages能收到。
     */
    public void send() {
        String msg1 = "I am topic.mesaage msg======";
        System.out.println("sender1 : " + msg1);
        this.rabbitTemplate.convertAndSend("exchange", "topic.message", msg1);

        String msg2 = "I am topic.mesaages msg########";
        System.out.println("sender2 : " + msg2);
        this.rabbitTemplate.convertAndSend("exchange", "topic.messages", msg2);
    }
}

