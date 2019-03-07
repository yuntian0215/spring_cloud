package com.yuntian.parentproject.service.impl;

import com.yuntian.parentproject.mapper.UserMapper;
import com.yuntian.parentproject.pojo.User;
import com.yuntian.parentproject.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>内容</p>
 * 2019/2/18/0018 9:16
 *
 * @author lvjie
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User findUserByUserName(String username) {
        return userMapper.findByUserName(username);
    }
}
