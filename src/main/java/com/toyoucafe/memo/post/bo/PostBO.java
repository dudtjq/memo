package com.toyoucafe.memo.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.toyoucafe.memo.common.FileManagerService;
import com.toyoucafe.memo.post.dao.PostDAO;
import com.toyoucafe.memo.post.model.Post;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	
	public int addPost(int userId, String title, String content, MultipartFile file) {
		
		String imagePath = FileManagerService.saveFile(userId, file);
		
		return postDAO.insertPost(userId, title, content, imagePath);
	}
	
	public List<Post> getPostList(int userId) {
		return postDAO.selectPostList(userId);
	}
	
	public Post getPost(int postId) {
		return postDAO.selectPost(postId);
	}
	
	public int updatePost(int postId, String title, String content) {
		
		return postDAO.updatePost(postId, title, content);
		
	}
	
	public int deletePost(int postId) {
		
		Post post = postDAO.selectPost(postId);
		FileManagerService.removeFile(post.getImagePath());
		
		
		return postDAO.deletePost(postId);
		
	}

}
