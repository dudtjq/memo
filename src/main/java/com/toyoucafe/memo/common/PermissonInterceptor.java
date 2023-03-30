package com.toyoucafe.memo.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class PermissonInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(
			HttpServletRequest request
			, HttpServletResponse response
			, Object handler
			) throws IOException {
		
		HttpSession session = request.getSession();
		
		Integer userId = (Integer)session.getAttribute("userId");
		
		// user/signin/view
		// 반복문 안에 써야 하니 위에다 작성 해둠
		String uri = request.getRequestURI();
		
		if(userId != null) {
			// 로그인 되었을때
			// 회워 가입, 로그인 페이지, 접근하려고 하면
			// /user 로 시작하는 페이지에 접근하려고 하면
			// 타임라인 페이지로 이동시켜라
			if(uri.startsWith("/user")) {
				// 리다이렉트
				response.sendRedirect("/post/list/view");
				return false;
			}else {
				
			}
			
			
		}else {
			// 로그인이 안되어있을때
			// 타임라인으로 접근 하려고 하면
			// /post 로 시작하는 페이지에 접근하려고 하면
			// 로그인 페이지로 이동 시켜라
			if(uri.startsWith("/post")) {
				response.sendRedirect("/user/signin/view");
				return false;
			}
		}
		
		return true;
		
		
		
		
		
	}

}
