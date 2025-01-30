package com.example.post.service.user;

import com.example.post.model.users.RegisterUserDto;
import com.example.post.model.users.User;
import com.example.post.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface UserService  {

    // username 으로 User 찾기
    public User getUserByUsername(String username);

    // 회원가입 처리
    public void registerUser(RegisterUserDto registerUserDto);
}
