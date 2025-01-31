package com.example.post.controller;

import com.example.post.model.posts.CreatePostDto;
import com.example.post.model.posts.EditPostDto;
import com.example.post.model.posts.Post;
import com.example.post.model.users.User;
import com.example.post.service.post.PostService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
public class PostController {

	@Autowired
	private PostService postService;

	// 글 쓰기 폼 이동
    @GetMapping(path = "posts/create")
	public String createPostForm(
			@SessionAttribute(name = "loginUser", required = false) User loginUser ,
			Model model){
		log.info("---- GET : createPost()");
		log.info("-- 현재 로그인됨 : {}", loginUser);

		// 로그인 여부확인
		if(loginUser == null){
			log.info("-- 로그인 세션 == null");
			return "redirect:/";
		}

		model.addAttribute("createPostDto", new CreatePostDto());

		return "posts/create";
	}

	// 글 작성
	@PostMapping("posts/create")
	public String createPost(@ModelAttribute @Validated CreatePostDto createPostDto,
							 BindingResult bindingResult,
							 @SessionAttribute(name="loginUser") User loginUser
							 ){
		log.info("---- POST : 글쓰기 요청");
		log.info("-- 글 내용 : {}",createPostDto);
		if(bindingResult.hasErrors()){
			log.info("-- 벨리데이션 실패");
			return "posts/create";
		}

		createPostDto.setUser(loginUser);
		log.info("글쓴이 : {}",createPostDto.getUser());

		postService.createPost(createPostDto);
		log.info("-- 글 작성 결과 : {}",createPostDto);


		return "redirect:/posts";
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

	// 글 하나 조회
	@Transactional
	@GetMapping("posts/{postId}")
	public String viewPost(@PathVariable(name = "postId") Long postId,
						   Model model){
		log.info("---- GET : 글 조회");

		// 조회수 1증가
		postService.increaseViews(postId);

		// 포스트 ID 로 포스트 찾기
		Post findPost = postService.getPostById(postId);
		log.info("-- 찾은글 : {}" , findPost);


		model.addAttribute("post", findPost);
		return"posts/view";
	}

	// 글삭제
	@GetMapping("posts/remove/{postId}")
	public String removePost(
			@PathVariable(name = "postId") Long postId,
			@SessionAttribute(name="loginUser",required = false) User loginUser){
		log.info("---- GET : 글 삭제");
		log.info("-- 로그인 유저 : {} ", loginUser);

		// 글이 있는지 조회
		Post findPost = postService.getPostById(postId);
		log.info("-- 찾은글 : {}" , findPost);
		log.info("-- 글쓴 유저 : {}",findPost.getUser());

		// 지금 로그인 한 사람 == 글쓴이 인지 체크
		if(findPost.getUser().equals(loginUser)){
			log.info("-- 알림 : 유저일치");
			postService.removePost(postId);
		} else {
			log.info("-- 알림 : 유저 불일치");
			return "redirect:/posts";
		}
		return "redirect:/posts";
	}

	// 글 수정 폼 이동
	@GetMapping("posts/edit/{postId}")
	public String editPostForm(
			@PathVariable(name = "postId") Long postId,
			@SessionAttribute(name="loginUser")User loginUser,
			Model model){
		log.info("---- GET : 글 수정 폼 이동");
		log.info("-- 로그인 유저 : {} ", loginUser);

		// 글이 있는지 조회
		Post findPost = postService.getPostById(postId);
		log.info("-- 찾은글 : {}" , findPost);
		log.info("-- 글쓴 유저 : {}",findPost.getUser());

		// 지금 로그인 한 사람 == 글쓴이 인지 체크
		if(findPost.getUser().equals(loginUser)){
			log.info("-- 알림 : 유저일치");
			// 기존 글 가져와서 빈 DTO 모델에 담기
			EditPostDto editPostDto = new EditPostDto();	// 빈 DTO 생성

			editPostDto.setTitle(findPost.getTitle());		// 제목
			editPostDto.setContent(findPost.getContent());	// 내용

			model.addAttribute("editPostDto", editPostDto);
			model.addAttribute("findPost", findPost);
		} else {
			log.info("-- 알림 : 유저 불일치");
			return "redirect:/posts";
		}
		return "posts/edit";
	}

	// 글 수정 업데이트
	@Transactional
	@PostMapping("posts/edit/{postId}")
	public String editPost(
			@ModelAttribute(name="editPostDto") @Validated EditPostDto editPostDto,
			BindingResult bindingResult,
			@SessionAttribute(name="loginUser", required = false) User loginUser,
			@PathVariable(name = "postId") Long postId,
			Model model
	){

		if(bindingResult.hasErrors()){
			log.info("-- 벨리데이션 실패");
			Post findPost = postService.getPostById(postId);
			model.addAttribute("findPost",findPost);

			return "posts/edit";
		}
		// 글 조회

		Post findPost = postService.getPostById(postId);
		log.info("-- 수정대상 : {}" , findPost);

		// 글쓴이 == 로그인 인지 확인
		if(findPost!=null && findPost.getUser().equals(loginUser)){
			postService.editPost(findPost,editPostDto);
		}
		return "redirect:/posts";
	}
}
