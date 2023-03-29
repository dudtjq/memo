package com.toyoucafe.memo.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.toyoucafe.memo.post.bo.PostBO;

@RestController
@RequestMapping("/post")
public class PostRestController {

	@Autowired
	private PostBO postBO;
	
	@PostMapping("/create")
	public Map<String, String> postCreate(
			@RequestParam("title") String title
			, @RequestParam("content") String content
			, @RequestParam(value="file", required=false) MultipartFile file
			, HttpServletRequest request) {
		
		// 로그인된 사용자의 user 테이블 id 컬럼 값
		HttpSession session = request.getSession();
		
		int userId = (Integer)session.getAttribute("userId");
		
		Map<String, String> resultMap = new HashMap<>();
		int count = postBO.addPost(userId, title, content, file);
		
		if(count == 1) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
	}
	
	@PostMapping("/update")
	public Map<String, String> modifyPost(
			@RequestParam("postId") int postId
			, @RequestParam("title") String title
			, @RequestParam("content") String content
			) {
		
		int count = postBO.updatePost(postId, title, content);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(count == 1) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "fail");
		}
		 
		return resultMap;
		
	}
	
	@GetMapping("/delete")
	public Map<String, String> deletePost(
			@RequestParam("postId") int postId) {
		
	 	int count = postBO.deletePost(postId);
	 	
	 	Map<String, String> resultMap = new HashMap<>();
	 	
	 	if(count == 1) {
	 		resultMap.put("result", "success");
	 	}else {
	 		resultMap.put("result", "fail");
	 	}
		
	 	return resultMap;
		
	}
	

	
}
