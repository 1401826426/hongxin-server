package com.taotao.manage.manage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

public class LoginInterceptor implements HandlerInterceptor{
	
	
	private UrlPathHelper urlPathHelper = new UrlPathHelper() ;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = urlPathHelper.getLookupPathForRequest(request) ; 
		if(!org.springframework.util.StringUtils.isEmpty(url) && ("/login".equals(url) || "/register".equals(url) || url.contains("image"))){
			return true; 
		}
		String sessionId = request.getHeader("session") ;
		if(StringUtils.isEmpty(sessionId)){
			response.setStatus(HttpStatus.FORBIDDEN.value());
			return false ; 
		}
		Session session = SessionManager.getInstance().touchSession(sessionId) ; 
		request.setAttribute("session", session);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
