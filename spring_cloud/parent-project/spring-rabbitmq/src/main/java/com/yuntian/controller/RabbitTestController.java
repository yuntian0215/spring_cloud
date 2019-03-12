package com.yuntian.controller;

import com.yuntian.hello.HelloSender1;
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
}
