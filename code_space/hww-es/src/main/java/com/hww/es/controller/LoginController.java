package com.hww.es.controller;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.hww.es.pojo.ucenter.UcenterUser;
import com.hww.es.pojo.ucenter.UCenterUserDTO;
import com.hww.es.service.ucenter.LoginService;
import com.hww.es.service.ucenter.UCenterUserService;
import com.hww.es.util.MD5;
import com.hww.es.util.R;

@RestController
public class LoginController {
	@Autowired
	LoginService loginService;
	@Autowired
	UCenterUserService userService;
	
//	@RequestMapping("/login")
//	@ResponseBody
//	public R login(UcenterUser user,HttpServletResponse response) throws Exception {
//		try {
//			String md5 = MD5.md5(user.getPassword());
//			user.setPassword(md5);
//			UCenterUserDTO userDto = userService.getUser(user);
//			if(userDto == null) {
//				return R.error("用户名或密码不正确");
//			}
//			String loginToken = loginService.loginToken(userDto, response);
//			userDto.setToken(loginToken);
//			return R.ok().put("data", userDto);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return R.error("系统繁忙");
//		}
//	}
	/**
	 * <p>登陆-获取权限组</p>
	 * <p>Description</p>
	 * @param user
	 * @param response
	 * @return
	 * @throws Exception
	 * 2018年10月23日上午8:29:04
	 * @author lvjie
	 */
	@RequestMapping("/login")
	@ResponseBody
	public R login(UcenterUser user,HttpServletResponse response) throws Exception {
		try {
			String phoneNumber = user.getPhoneNumber();
			if(phoneNumber.contains("@")) {
				user.setEmail(phoneNumber);
				user.setPhoneNumber(null);
			}
			String md5 = MD5.md5(user.getPassword());
			user.setPassword(md5);
			UcenterUser cuser = userService.getUcenterUser(user);
			return R.ok().put("data", cuser);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error("系统繁忙");
		}
	}
	
	@RequestMapping("/logout")
	@ResponseBody
	public R logout(String token) throws Exception {
		try {
			loginService.logoutToken(token);
			return R.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return R.error("系统繁忙");
		}
	}
}
