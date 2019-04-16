package com.yuntian.controller;

import com.yuntian.model.User;
import com.yuntian.service.IUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>定义控制层</p>
 * 2019/4/16/0016 16:47
 * 说明repository层里很多方法（自定义的方法）没有写到service，需要的可以自己写
 * @author lvjie
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;
    /**
     * <p>往mongo添加数据，方便之处就在于你不需要去建对应的user表，代码自动映射给你创建好了</p>
     * @return
     * 2019年4月16日下午4:22:56
     * @author lvjie
     */
    @RequestMapping("/save")
    public String save(){
        User user1 = new User(1,"zhangsan","18");
        User user2 = new User(2,"lisi","19");
        User user3 = new User(3,"wangwu","20");
        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        return "添加成功！";
    }
    /**
     * <p>查询数据</p>
     * @return
     * 2019年4月16日下午4:24:02
     * @author lvjie
     */
    @RequestMapping("/findAll")
    public List<User> findAll(){
        return userService.findList();
    }
    /**
     * <p>模糊查询</p>
     * @return 根据规则自定义查询
     * 2019年4月16日下午4:33:44
     * @author lvjie
     */
    @RequestMapping("/findUserByName")
    public List<User> findUserByName(){
        return userService.findUserByName("zhang");
    }
}
