package com.example.post.controller;

import com.example.post.model.posts.CreatePostDto;
import com.example.post.model.posts.Post;
import com.example.post.model.users.User;
import com.example.post.service.post.PostService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
public class PostController {

	@Autowired
	private PostService postService;

	// 글 쓰기 폼 이동
    @GetMapping(path = "posts/create")
	public String createPostForm(HttpSession session,
								 Model model){
		log.info("---- GET : createPost()");
		log.info("-- 현재 로그인됨 : {}", session.getAttribute("loginUser"));

		// 로그인 여부확인
		if(session.getAttribute("loginUser") == null){
			log.info("-- 로그인 세션 == null");
			return "redirect:/";
		}

		model.addAttribute("createPostDto", new CreatePostDto());

		return "posts/create";
	}

	// 글 작성
	@PostMapping("posts/create")
	public String createPost(@ModelAttribute CreatePostDto createPostDto,
							 HttpSession session){
		log.info("---- POST : 글쓰기 요청");
		log.info("-- 글 내용 : {}",createPostDto);

		createPostDto.setUser((User) session.getAttribute("loginUser"));
		log.info("글쓴이 : {}",createPostDto.getUser());

		postService.createPost(createPostDto);
		log.info("-- 글 작성 결과 : {}",createPostDto);


		return "redirect:/";
	}




	// 글 목록보기
	@GetMapping("posts")
	public String postList(Model model){
		log.info("---- GET : 글 리스트 이동");
		List<Post> posts = postService.getAllPosts();
		log.info("-- 글목록 : {}", posts);
		model.addAttribute("posts",posts);

		return "posts/list";
	}

	@GetMapping("posts/{postId}")
	public String viewPost(@PathVariable(name = "postId") Long postId,
						   Model model){


		return"posts/view";
	}

}
