package com.example.post.service.user;

import com.example.post.model.users.RegisterUserDto;
import com.example.post.model.users.User;
import com.example.post.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    // username 으로 User 찾기
    @Override
    public User getUserByUsername(String username){
        User findUser = userRepository.findByUsername(username);
        return findUser;
    }

    @Override
    public void registerUser(RegisterUserDto registerUserDto) {
        log.info("-- 서비스 : 회원가입 시작");
        User registerUser = new User();
        registerUser.setUsername(registerUserDto.getUsername());
        registerUser.setPassword(registerUserDto.getPassword());
        registerUser.setName(registerUserDto.getName());
        registerUser.setEmail(registerUserDto.getEmail());
        registerUser.setGenderType(registerUserDto.getGenderType());
        registerUser.setBirthDate(registerUserDto.getBirthDate());

        log.info("-- 입력됨 {}", registerUser);

        userRepository.save(registerUser);

    }

    @Override
    public boolean checkUsername(String username) {
        log.info("-- 서비스 : username 중복체크");

        // username 으로 User 찾기 (존재하는지확인)
        User findUser = userRepository.findByUsername(username);

        // 이미 있을때
        if(findUser.getUsername() == username){
            log.info("-- username 중복 있음");
            return true;
        }

        //없을때
        log.info("-- username 중복 없음");
        return false;
    }

}
