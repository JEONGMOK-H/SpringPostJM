package com.example.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.post.model.users.User;



public interface UserRepository extends JpaRepository<User, Long> { // <타입 , 아이디 PK 타입>
	// Username 으로회원정보
	public User findByUsername(String username);

}
