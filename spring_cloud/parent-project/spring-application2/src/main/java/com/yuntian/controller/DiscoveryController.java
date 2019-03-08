package com.yuntian.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.yuntian.Order;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>发现获取Eureka中注册的服务</p>
 * 2019/3/7/0007 15:57
 *
 * @author lvjie
 */
@RestController
@RequestMapping("/discovery")
public class DiscoveryController {
    @Resource
    private EurekaClient client;

    /**
    *@Description 获取服务信息
    *@Param [id]
    *@Return java.lang.Object
    *@Author lvjie
    *@Date 2019/3/7/0007 16:14
    */
    @RequestMapping("/getOrder1/{id}")
    @ResponseBody
    public Object  getOrder1(@PathVariable Integer id){
        // 获取Eureka中所有的服务节点
        List<Application> applications = client.getApplications().getRegisteredApplications();
        if (applications != null) {
            for (Application application : applications) {
                // 对外暴露的服务名称
                String name = application.getName();
                // 只看订单服务信息
                if ("APP1".equals(name)) {
                    // 服务有多少个实例，比如订单服务可能部署了多个，有多个订单服务注册到了eureka
                    List<InstanceInfo> instances = application.getInstances();
                    System.out.println("所有的订单服务：{}"+instances);
                    if (instances != null) {
                        for (InstanceInfo info : instances) {
                            System.out.println("服务id：{}"+info.getInstanceId());
                            System.out.println("服务主机：{}"+info.getHostName());
                            System.out.println("服务端口：{}"+info.getPort());
                        }
                    }
                    return instances;
                }
            }
        }
        return null;
    }
}
