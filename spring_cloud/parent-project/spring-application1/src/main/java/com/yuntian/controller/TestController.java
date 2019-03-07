package com.yuntian.controller;

import com.yuntian.pojo.Order;
import com.yuntian.service.ITestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * <p>内容</p>
 * 2019/3/1/0001 15:14
 *
 * @author lvjie
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @Resource
    private ITestService testService;

    @RequestMapping("/getOrder/{id}")
    @ResponseBody
    public Order getOrder(@PathVariable Integer id){
        Order order = testService.getOrderById(id);
        return order;
    }
}
