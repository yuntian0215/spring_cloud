package com.yuntian.controller;

import com.yuntian.User;
import com.yuntian.service.IUserService;
import com.yuntian.utils.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>内容</p>
 * 2019/3/11/0011 14:04
 *
 * @author lvjie
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;
    /**
    *@Description http://localhost:8070/user/getList?token=1202fada-3322-41a9-9914-ce2732b3eda1  这样访问才正确，否则无权限
    *@Param []
    *@Return com.yuntian.utils.R
    *@Author lvjie
    *@Date 2019/3/11/0011 14:16
    */
    @RequestMapping("/getList")
    public R getUser(){
        List<User> list = userService.findUserList();
        return R.ok().put("data",list);
    }
}
