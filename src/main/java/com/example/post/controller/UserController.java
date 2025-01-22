package com.example.post.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.post.model.users.User;
import com.example.post.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// 회원가입 페이지 요청 처리
	@GetMapping("users/register")
	public String resister() {
		
		return "users/register";
	}
	 
	// 회원가입 요청처리
	@PostMapping("users/register")
	public String registerUser(
		@ModelAttribute	User user) {
		log.info("----POST 회원가입 메서드 시작-----");
		log.info("user : {}" , user);
		User registedUser = userService.registerUser(user);
		
		log.info("registedUser : {}" , registedUser);
		
		return "redirect:/";
	}
	
	// 로그인 페이지이동
	@GetMapping("users/login")
	public String loginFrom() {
		
		return "users/login";
	}
	
	// 로그인
	@PostMapping("users/login")
	public String login(
			@ModelAttribute User user,
			HttpServletRequest request) {
		
		log.info("-----POST : login-----");
		// username에 해당하는 User 객체를 찾는다
		
		User findUser = userService.getUserByUsername(user.getUsername());
		log.info("findUser : {}", findUser);
		
		// 입력된 username, password가 DB에서 찾은 User 정보와 일치하는지 확인
		if(findUser == null || !findUser.getPassword().equals(user.getPassword())) {
			return "redirect:/users/login";
		}
		
		// Request 객체에 저장되있는 Session 객체를 받아온다 ? 왜있는거지
		HttpSession session = request.getSession();
		
		// session 에 로그인정보 저장
		session.setAttribute("loginUser", findUser);
		
		
		
		
		return "redirect:/";
	}
	
	//session 정보 확인
	@ResponseBody
	@GetMapping("logincheck")
	public String loginCheck(HttpServletRequest request) {
		log.info("----로그인체크-----");
		HttpSession session = request.getSession();
		String loginUsername = (String)session.getAttribute("loginUsername"); 
		// 오브젝트로 반환되서 형변환 필요
		// 오브젝트 -> 스트링 형변환 : 자식개체로 형변환이므로 (大→小형 변환) 강제형 변환 필요
		log.info("loginUsername : {} ", loginUsername);
		
		
		return "ㅇㅋ";
	}
	
	// 로그아웃
	@GetMapping("users/logout")
	public String logout(HttpSession session) {
		log.info("-----로그아웃-----");
		
//		session.setAttribute("loginUser", null);
		session.invalidate();
	
		return "redirect:/";
	}

	
}
