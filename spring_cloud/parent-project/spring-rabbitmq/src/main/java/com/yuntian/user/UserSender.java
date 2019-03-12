package com.yuntian.user;

import com.yuntian.RabbitUser;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>实体类传输-生成者</p>
 * 2019/3/12/0012 16:28
 *
 * @author lvjie
 */
@Component
public class UserSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        RabbitUser user=new RabbitUser();
        user.setName("yuntian");
        user.setPass("123456789");
        System.out.println("user send : " + user.getName()+"/"+user.getPass());
        //指定往user队列发送消息
        this.rabbitTemplate.convertAndSend("user", user);
    }
}
