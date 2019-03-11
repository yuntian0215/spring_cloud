package com.yuntian.service;

import com.yuntian.User;

import java.util.List;

/**
 * Package: com.yuntian.service
 * <p>
 * Description： 验证登陆用户
 * <p>
 * Author: lvjie
 * <p>
 * Date: Created in 2019/3/8/0008 14:12
 * <p>
 * Version: 0.0.1
 */
public interface IUserService {
    public User findByUsername(String userName);
    List<User> findUserList();
}
