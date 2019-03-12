package com.yuntian.user;

import com.yuntian.RabbitUser;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * <p>实体类传输-消费者</p>
 * 2019/3/12/0012 16:31
 *
 * @author lvjie
 */
@Component
@RabbitListener(queues = "user")//指定监控的队列名
public class UserReceiver {
    @RabbitHandler
    public void process(RabbitUser user) {
        System.out.println("user receive  : " + user.getName()+"/"+user.getPass());
    }
}
