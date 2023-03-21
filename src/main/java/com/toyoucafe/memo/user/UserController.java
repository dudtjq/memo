package com.toyoucafe.memo.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

	@GetMapping("/signup/view")
	public String signupInput() {
		return "user/signup";
	}
	
	@GetMapping("/signin/view")
	public String signinInput() {
		return "user/signin";
	}
	// 로그아웃 기능은 레스트 컨드롤러가 아닌 일반 컨드롤러 에서 처리
	@GetMapping("/signout")
	public String signout(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		// 로그아웃 기능은 리무브를 활용
		session.removeAttribute("userId");
		session.removeAttribute("userName");
		// 로그아웃 기능에서 리다이렉트를 사용한다! 뒤에는 로그인 주소를 사용!
		return "redirect:/signin/view";
		
	}
}
