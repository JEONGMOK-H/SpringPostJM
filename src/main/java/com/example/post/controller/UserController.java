package com.example.post.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.post.model.User;
import com.example.post.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//회원가입 페이지 요청 처리
	@GetMapping(path = "register")
	public String resister() {
		
		return "register";
	}
	 
	//회원가입 요청처리
	@PostMapping(path = "register_v3")
	public String registerUser(
		@ModelAttribute	User user) {
		log.info("user : {}" , user);
		User registedUser = userService.registerUser(user);
		
		log.info("registedUser : {}" , registedUser);
		
		return "register_success";
	}
	
	// ID로 회원정보 조회하기
	@GetMapping(path = "user-details/{id}")
	public String userDetails(
		@PathVariable(name = "id") Long id,
		Model model) {
		
		User user = userService.getUserById(id);
		
		//검색한 User 정보를 Model에 담는다 
		model.addAttribute("user", user);
		
		return "user_detail";
	}
	
	// User List 
	@GetMapping(path = "user-list")
	public String userList(Model model) {
		model.addAttribute("users", userService.getAllUsers());
		
		return "user_list";
	}



}
