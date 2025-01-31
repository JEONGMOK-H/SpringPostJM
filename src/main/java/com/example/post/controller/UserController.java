package com.example.post.controller;

import com.example.post.model.users.LoginUserDto;
import com.example.post.model.users.RegisterUserDto;
import com.example.post.model.users.User;
import com.example.post.service.user.UserService;
import jakarta.persistence.Id;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    // 로그인페이지 이동
    @GetMapping("users/login")
    public String loginForm(Model model){
        log.info("---- GET : 로그인 폼 이동");

        // HTML에 보내기 위해 빈 로그인 객체 만들어서 모델에 저장
        model.addAttribute("loginUserDto", new LoginUserDto());

        return "users/login";
    }

    // 로그인 받기
    @PostMapping("users/login")
    public String login(@ModelAttribute @Validated LoginUserDto loginUserDto,
                        BindingResult bindingResult,
                        HttpServletRequest request){
        log.info("---- Post : 로그인 시작");

        if (bindingResult.hasErrors()){
            log.info("-- 벨리데이션 실패");

            return "users/login";
        }



        log.info("입력됨 : {}",loginUserDto);

        // 입력된 유저가 DB에 있는지 체크
        User findUser = userService.getUserByUsername(loginUserDto.getUsername());
        log.info("-- 찾은유저 : {}", findUser);

        if(findUser == null || !findUser.getPassword().equals(loginUserDto.getPassword())){
            log.info("-- null 이거나 패스워드 오류");
            bindingResult.reject("loginFail","아이디 또는 비밀번호 오류");
            return "users/login";
        }

        // 내가 보낸 request 객체에서 세션가져와서 HttpSession에 넣음
        HttpSession session =request.getSession();

        // 서버 session 에 로그인 객체 저장 (loginUser 라는 변수명으로 findUser 객체 저장
        session.setAttribute("loginUser",findUser);

        return "redirect:/";
    }

    // 로그아웃
    @GetMapping("users/logout")
    public String logout(HttpSession session){
        log.info("---- GET : 로그아웃");
//        session.setAttribute("loginUser",null);
        session.invalidate();

        return "redirect:/";
    }

    // 회원가입 페이지 이동
    @GetMapping("users/register")
    public String registerForm(Model model){
        log.info("---- GET : 회원가입 페이지 이동");
        model.addAttribute("registerUserDto", new RegisterUserDto());

        return "users/register";
    }

    // 회원가입 POST
    @PostMapping("users/register")
    public String register(@ModelAttribute @Validated RegisterUserDto registerUserDto,
                           BindingResult bindingResult,
                           Model model){
        log.info("---- POST : 회원가입");
        // 벨리데이션
        if(bindingResult.hasErrors()){
            log.info("-- 벨리데이션 실패");
            model.addAttribute("registerUserDto",registerUserDto);

            return "users/register";
        }

        log.info("입력됨 : {}",registerUserDto);

        // 아이디 중복 확인 boolean 반환
        boolean usernameTaken = userService.checkUsername(registerUserDto.getUsername());

        if(usernameTaken){

            return "";
        }

        userService.registerUser(registerUserDto);

        log.info("-- 회원가입성공");

        return "redirect:/users/login";
    }
}
