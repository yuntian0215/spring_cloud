package com.yuntian.controller;

import com.yuntian.model.User;
import com.yuntian.producer.FirstProducer;
import com.yuntian.producer.UserProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>内容</p>
 * 2019/4/18/0018 14:07
 *
 * @author lvjie
 */
@RestController
public class KafkaController {
    @Resource
    private FirstProducer firstProducer;
    @Resource
    private UserProducer userProducer;
    @RequestMapping("/send")
    public String send(String msg){
        firstProducer.send(msg);
        return "发送消息成功";
    }
    /**
     * <p>测试发送对象信息</p>
     * @return
     * 2019年4月22日上午9:35:17
     * @author lvjie
     */
    @RequestMapping("/sendUser")
    public String sendUser(){
        for(int i=0;i<100;i++){
            User user = new User();
            user.setId(i+1);
            user.setName("测试对象");
            user.setAddress("测试地址");
            userProducer.sendUser(user);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return "发送对象成功！";
    }
}
