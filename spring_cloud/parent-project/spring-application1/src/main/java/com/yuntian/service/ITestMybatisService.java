package com.yuntian.service;

import com.yuntian.User;

import java.util.List;

/**
 * Package: com.yuntian.service
 * <p>
 * Description： 测试集成mybatis
 * <p>
 * Author: lvjie
 * <p>
 * Date: Created in 2019/3/8/0008 10:43
 * <p>
 * Version: 0.0.1
 */
public interface ITestMybatisService {
    public List<User> selectList();
}
