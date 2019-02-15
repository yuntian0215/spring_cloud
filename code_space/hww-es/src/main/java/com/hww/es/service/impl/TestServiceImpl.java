//package com.hww.es.service.impl;
//
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Service;
//
//import com.hww.es.mapper.UserMapper;
//import com.hww.es.pojo.User;
//import com.hww.es.pojo.UserExample;
//import com.hww.es.service.ITestService;
//@Service
//public class TestServiceImpl implements ITestService{
//	@Resource
//	private UserMapper userMapper;
//
//	@Override
//	public List<User> getUser() {
//		UserExample ex = new UserExample();
//		return userMapper.selectByExample(ex);
//	}
//}
