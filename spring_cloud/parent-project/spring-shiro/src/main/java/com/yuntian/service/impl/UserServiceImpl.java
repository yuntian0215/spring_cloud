package com.yuntian.service.impl;

import com.yuntian.User;
import com.yuntian.mapper.UserMapper;
import com.yuntian.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>验证登陆用户</p>
 * 2019/3/8/0008 14:12
 *
 * @author lvjie
 */
@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User findByUsername(String userName) {
        return userMapper.selectByUsername(userName);
    }

    @Override
    public List<User> findUserList() {
        return userMapper.selectUserList();
    }
}
