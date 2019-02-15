//package com.hww.es.controller;
//
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.hww.es.pojo.User;
//import com.hww.es.service.ITestService;
//
//@Controller
//@RequestMapping("/test")
//public class TestController {
//	@Resource
//	private ITestService testService;
//	@RequestMapping("/getUser")
//	@ResponseBody
//	public List<User> getUser(){
//		List<User> list = testService.getUser();
//		return list;
//	}
//}
