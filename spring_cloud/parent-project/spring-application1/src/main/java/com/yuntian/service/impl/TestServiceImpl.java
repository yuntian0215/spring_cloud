package com.yuntian.service.impl;

import com.yuntian.pojo.Order;
import com.yuntian.service.ITestService;
import org.springframework.stereotype.Service;

/**
 * <p>内容</p>
 * 2019/3/1/0001 15:15
 *
 * @author lvjie
 */
@Service
public class TestServiceImpl implements ITestService {
    @Override
    public Order getOrderById(Integer id) {
        Order order = new Order();
        order.setId(id);
        order.setOrderName("dadada");
        order.setOrderUrl("www");
        return order;
    }
}
