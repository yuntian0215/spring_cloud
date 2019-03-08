package com.yuntian.service.impl;

import com.yuntian.User;
import com.yuntian.mapper.UserMapper;
import com.yuntian.service.ITestMybatisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>测试集成mybatis</p>
 * 2019/3/8/0008 10:44
 *
 * @author lvjie
 */
@Service
public class TestMybatisServiceImpl implements ITestMybatisService {
    @Resource
    private UserMapper userMapper;
    /**
    *@Description 获取所有数据集合
    *@Param []
    *@Return java.util.List<com.yuntian.User>
    *@Author lvjie
    *@Date 2019/3/8/0008 10:47
    */
    public List<User> selectList(){
        List<User> list = userMapper.selectList();
        return list;
    }
}
