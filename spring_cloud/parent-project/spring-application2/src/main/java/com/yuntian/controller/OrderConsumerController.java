package com.yuntian.controller;

import com.yuntian.pojo.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * <p>内容</p>
 * 2019/3/1/0001 15:48
 *
 * @author lvjie
 */
@Controller
@RequestMapping("/order/consumer")
public class OrderConsumerController {
    @Value("${ORDER_URL}")
    private String ORDER_URL;
    @Resource
    private RestTemplate restTemplate;
    @RequestMapping("/getOrder/{id}")
    @ResponseBody
    public Order getOrder(@PathVariable Integer id){
        return restTemplate.getForObject(ORDER_URL+"/test/getOrder/1",Order.class);
    }
}
