package com.yuntian.controller;

import com.yuntian.callback.CallBackSender;
import com.yuntian.fanout.FanoutSender;
import com.yuntian.hello.HelloSender1;
import com.yuntian.topic.TopicSender;
import com.yuntian.user.UserSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>内容</p>
 * 2019/3/12/0012 15:25
 *
 * @author lvjie
 */
@RestController
@RequestMapping("/rabbit")
public class RabbitTestController {
    @Autowired
    private HelloSender1 helloSender1;
    @Autowired
    private UserSender userSender;
    @Autowired
    private TopicSender topicSender;
    @Autowired
    private FanoutSender fanoutSender;
    @Autowired
    private CallBackSender callBackSender;
    //单生产者单消费者
    @RequestMapping("/hello")
    public void hello(){
        helloSender1.send("hello word");
    }
    //单生产者多消费者，也可以改成多生产者和多消费者
    //从结果可知，生产者发送的10条消息，分别被两个消费者平均接收了
    @RequestMapping("/oneToMany")
    public void oneToMany(){
        for(int i=0;i<10;i++){
            helloSender1.send("hellomsg:"+i);
        }
    }
    //实体类传输测试
    @RequestMapping("/userTest")
    public String userTest(){
        userSender.send();
        return "访问成功！";
    }
    /**
     * topic exchange类型rabbitmq测试
     * 结果可知：sender1发送的消息,routing_key是“topic.message”，所以exchange里面的绑定的binding_key是“topic.message”，topic.＃都符合路由规则;所以sender1
     * 发送的消息，两个队列都能接收到；
     *
     * sender2发送的消息，routing_key是“topic.messages”，所以exchange里面的绑定的binding_key只有topic.＃都符合路由规则;所以sender2发送的消息只有队列
     * topic.messages能收到。
     */
    @RequestMapping("/topicTest")
    public void topicTest() {
        topicSender.send();
    }
    /**
     * fanout exchange类型rabbitmq测试
     * 结果可知：就算fanoutSender发送消息的时候，指定了routing_key为"abcd.ee"，但是所有接收者都接受到了消息
     */
    @RequestMapping("/fanoutTest")
    public void fanoutTest() {
        fanoutSender.send();
    }
    //测试消息回调
    //消费者：topicMessagesReceiver
    @RequestMapping("/callback")
    public void callbak() {
        callBackSender.send();
    }
}
