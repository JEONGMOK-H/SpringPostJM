package com.example.post.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class PostController {

	// 글 쓰기
    @GetMapping(path = "posts/create")
	public String savaPost(){
		log.info("---- Get : savePost()");

		return "posts/create";
	}

	// 글 목록보기
	@GetMapping(path="posts")
	public String postList(){
		log.info("---- Get : savelist()");

		return "posts/list";
	}

}
