package com.toyoucafe.memo.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.toyoucafe.memo.user.bo.UserBO;
import com.toyoucafe.memo.user.model.User;

@RestController // @Controller - @ResponseBody 가 합쳐진 것임
@RequestMapping("/user")
public class UserRestController {

	@Autowired
	private UserBO userBO;
	
	@PostMapping("/signup")
	public Map<String, String> signup(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, @RequestParam("name") String name
			, @RequestParam("email") String email
			) {
		
		int count = userBO.addUser(loginId, password, name, email);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(count == 1) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
	}
	// 로그인 api
	@PostMapping("/signin")
	public Map<String, String> signin(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, HttpServletRequest request
			) {
		
		 User user = userBO.getUser(loginId, password);
		
		 Map<String, String> resultMap = new HashMap<>();
		 
		 // null을 사용한다.
		 if(user != null) {
			 resultMap.put("result", "success");
			 
			 HttpSession session = request.getSession();
			 session.setAttribute("userId", user.getId());
			 session.setAttribute("userName", user.getName());
			 
		 }else {
			 resultMap.put("result", "fail");
		 }
		 
		 return resultMap;
		 
	}
	
}
