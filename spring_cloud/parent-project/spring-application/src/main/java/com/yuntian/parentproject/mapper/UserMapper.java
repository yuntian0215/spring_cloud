package com.yuntian.parentproject.mapper;

import com.yuntian.parentproject.pojo.User;

/**
 * <p>内容</p>
 * 2019/2/18/0018 9:10
 *
 * @author lvjie
 */
public interface UserMapper {
    public User findByUserName(String username);
}
