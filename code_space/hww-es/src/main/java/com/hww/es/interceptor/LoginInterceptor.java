package com.hww.es.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.hww.es.service.impl.ucenter.LoginServiceImpl;
import com.hww.es.service.ucenter.LoginService;

public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	LoginService loginService;
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("-------------------preHandle");
		Boolean checkLogin = loginService.checkLoginToken(response, request);
		if(checkLogin) {
			return true;
		}
		System.out.println(request.getContextPath()+ "/");
		//response.sendRedirect("/");
		RequestDispatcher rd = request.getRequestDispatcher(request.getContextPath()+ "/");
		rd.forward(request, response);
		return false;
	}

}
