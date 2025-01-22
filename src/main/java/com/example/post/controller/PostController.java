package com.example.post.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.post.model.posts.Post;
import com.example.post.model.users.User;
import com.example.post.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class PostController {
	
	private final PostService postService;
	

	@GetMapping(path = "/posts/create")
	public String createPost(
			// 세션에 저장되어있는 데이터 조회
			@SessionAttribute(name="loginUser", required=false) User loginUser) {
		
		if(loginUser == null) {
			log.info("loginUser: ${}", loginUser);
			return "redirect:/users/login";
		}
	
		return "posts/create";
	}
	
	@PostMapping("posts")
	public String savePost(
			@ModelAttribute Post post,
			@SessionAttribute(name="loginUser") User loginUser) {
		
		log.info("-----PostMapping-----");
		log.info("Post {} : ", post);
		log.info("loginUser {} : ", loginUser);
		post.setUser(loginUser);
		postService.savePost(post); // Post 타입으로 리턴받았는데 그걸로 뭐하는가

		
		return "redirect:/posts";
	}
	
	// 게시글 목록 조회
	@GetMapping("posts")
	public String listPosts(Model model) {
		List<Post> posts = postService.getAllPosts();
		model.addAttribute("posts", posts);
		
		return "posts/list";
	}
	
	// 게시글 조회
	@GetMapping("posts/{postId}")
	public String viewPort(
			@PathVariable(name="postId") Long postId,
			Model model) {
		Post post= postService.getPostById(postId);
		model.addAttribute("post",post);
		
		return "posts/view";
		
	}
	
	// 게시글삭제
	@GetMapping("posts/remove/{postId}")
	public String removePost(
			@SessionAttribute(name="loginUser") User loginUser,
			@PathVariable(name="postId") Long postId) {
		
		log.info("-----게시글삭제-----");
		
		// 삭제하려고하는 게시글이 로그인 사용자가 작성한 글인지확인
		Post findPost = postService.getPostById(postId);
		// 로그인 사용자와 작성자가 다르면 삭재하지않고 목록 패이지로 리다이렉트한다.
		if(findPost == null || findPost.getUser().getId() != loginUser.getId()) {
			return "redirect:/posts/";
		}
		
		postService.removePost(postId);
		
		
		
	return "redirect:/posts";
	}

}
