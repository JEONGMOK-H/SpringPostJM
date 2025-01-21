package com.example.post.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.post.model.posts.Post;
import com.example.post.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class PostController {
	
	private final PostService postService;
	
	
	@GetMapping(path = "/posts/create")
	public String createPost() {
		
	
		return "posts/create";
	}
	
	@PostMapping("posts")
	public String savePost(
			@ModelAttribute Post post) {
		log.info("post {} : ", post);
		postService.savePost(post); // Post 타입으로 리턴받았는데 그걸로 뭐하는가
		log.info("post {} : ", post);
		log.info("getCreateTime : {} : ", post.getCreateTime());

		
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
	
	@PostMapping("posts/remove/{postId}")
	public String removePost(
			@PathVariable(name="postId") Long postId,
			@RequestParam(name="password") String passowrd) {
		postService.removePost(postId, passowrd);	
		
		
	return "redirect:/posts";
	}

}
