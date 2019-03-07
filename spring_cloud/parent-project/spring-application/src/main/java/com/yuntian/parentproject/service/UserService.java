package com.yuntian.parentproject.service;

import com.yuntian.parentproject.pojo.User;

/**
 * Package: com.yuntian.parentproject.service
 * <p>
 * Description： TODO
 * <p>
 * Author: lvjie
 * <p>
 * Date: Created in 2019/2/18/0018 9:15
 * <p>
 * Version: 0.0.1
 */
public interface UserService {
    public User findUserByUserName(String username);
}
