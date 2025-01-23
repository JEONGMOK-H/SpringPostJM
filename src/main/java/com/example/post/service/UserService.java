package com.example.post.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.post.model.users.User;
import com.example.post.repository.UserRepository;

@Service // 컴포넌트 스캔 대상으로 만들기 + 서비스로 만들기
public class UserService {
	
	/* 
	 * 의존성주입방법
	 * 1. 필드주입
	 * 2. 생성자 주입
	 * 3. 세터주입
	 * 
	 * Spring Data Jpa 의 CRUD
	 * Create 	: save(엔티티객체)
	 * Read		: findById(엔티티객체의 아이디) , findAll() 모든객체 조회
	 * Update	: 없음 (영속성 컨텍스트에서 더티체킹 으로 업데이트)
	 * Delete	: delete(엔티티객체)
	 * 
	 */

	@Autowired
	private UserRepository userRepository;
//	private UserRepository userRepository = new UserRepository();
	
	
	
	// 사용자 등록 메서드
	public User registerUser(User user) {
		return userRepository.save(user);
	}
	
	// 유저 ID로 정보찾기
	public User getUserById(Long id) {
		Optional<User> result = userRepository.findById(id);
//		if(result.isPresent()) {
//			return result.get();
//		}
//		throw new RuntimeException("회원정보가 없습니다.");
		
		return result.orElseThrow(() -> new RuntimeException("회원정보가 없습니다."));
		
				
	}
	
	// 전체 회원정보 조회 메서드
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}

	
	// username으로 User 찾기
	public User getUserByUsername(String username) {
		User findUser = userRepository.findByUsername(username);
		return findUser;
	}
	
	
}
