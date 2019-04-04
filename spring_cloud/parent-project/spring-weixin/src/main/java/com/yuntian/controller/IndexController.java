package com.yuntian.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * <p>处理返回页面</p>
 * 2019年4月3日下午4:56:09
 * @author lvjie
 */
@Controller
@RequestMapping("/index")
public class IndexController {
	/**
	*@Description 测试微信跳转页面
	*@Param []
	*@Return java.lang.String
	*@Author lvjie
	*@Date 2019/4/4/0004 14:05
	*/
	@RequestMapping("/login")
	public String login(){
		return "index";
	}
}
