package com.hww.es.service.ucenter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.hww.es.pojo.ucenter.UCenterUserDTO;

public interface LoginService {

	/**
	 * 登录
	 * @param user
	 * @param response
	 */
	public void loginCookie(UCenterUserDTO user, HttpServletResponse response);
	
	public String loginToken(UCenterUserDTO user, HttpServletResponse response);
	
	/**
	 * 注销
	 * @param user
	 * @param response
	 * @param request
	 */
	public void logoutCookie(HttpServletResponse response,HttpServletRequest request);
	public void logoutToken(String token);
	
	/**
	 * 是否登录
	 * @param user
	 * @param response
	 * @param request
	 */
	public Boolean checkLoginCookie( HttpServletResponse response,HttpServletRequest request);
	public Boolean checkLoginToken(HttpServletResponse response, HttpServletRequest request);

	
}
