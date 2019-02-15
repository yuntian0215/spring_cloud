package com.hww.es.service.impl.ucenter;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hww.es.pojo.ucenter.UCenterUserDTO;
import com.hww.es.service.JedisService;
import com.hww.es.service.ucenter.LoginService;
import com.hww.es.util.CookieUtils;

@Service
public class LoginServiceImpl implements LoginService {

	private final int expiredTime = 86400; 
	
	@Autowired
	JedisService jedisService; 
	
	@Override
	public void loginCookie(UCenterUserDTO user,HttpServletResponse response) {
		String uuid = UUID.randomUUID().toString();
		CookieUtils.addCookie(response, "loginKey",uuid, expiredTime);
		String redisKey = "loginKey_" + uuid;
		jedisService.hset(redisKey, "userName", user.getUserName());
		jedisService.hset(redisKey, "userId", user.getId().toString());
		jedisService.hset(redisKey, "phoneNumber", user.getPhoneNumber());
		jedisService.hset(redisKey, "email", user.getEmail());
		jedisService.hset(redisKey, "companyId", user.getCompanyId().toString());
		jedisService.hset(redisKey, "companyName", user.getCompanyName());
		jedisService.expire(redisKey, expiredTime);
	}
	@Override
	public String loginToken(UCenterUserDTO user, HttpServletResponse response) {
		String uuid = UUID.randomUUID().toString();
		String redisKey = "loginKey_" + uuid;
		jedisService.hset(redisKey, "userName", user.getUserName());
		jedisService.hset(redisKey, "userId", user.getId().toString());
		jedisService.hset(redisKey, "phoneNumber", user.getPhoneNumber());
		jedisService.hset(redisKey, "email", user.getEmail());
		jedisService.hset(redisKey, "companyId", user.getCompanyId().toString());
		jedisService.hset(redisKey, "companyName", user.getCompanyName());
		jedisService.expire(redisKey, expiredTime);
		return uuid;
	}
	@Override
	public void logoutCookie(HttpServletResponse response, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Cookie cookie = CookieUtils.getCookieByName(request, "loginKey");
		if(cookie == null) {
			return;
		}
		String uuid = cookie.getValue();
		CookieUtils.delCookie(request, response, "loginKey");
		jedisService.del("loginKey_"+uuid);
	}
	
	@Override
	public void logoutToken(String token) {
		jedisService.del("loginKey_"+token);
	}

	@Override
	public Boolean checkLoginCookie(HttpServletResponse response, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Cookie cookie = CookieUtils.getCookieByName(request, "loginKey");
		if(cookie == null) {
			return false;
		}
		String uuid = cookie.getValue();
		Boolean exists = jedisService.exists("loginKey_"+uuid);
		return exists;
	}
	@Override
	public Boolean checkLoginToken(HttpServletResponse response, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		String token = request.getParameter("token");
		if(token == null) {
			return false;
		}
		Boolean exists = jedisService.exists("loginKey_"+token);
		return exists;
	}

	

}
