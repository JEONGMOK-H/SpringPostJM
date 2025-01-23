package com.example.post.model.users;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class User {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // PK Column 회원정보(data)를 구분하는 아이디 값
	private String name;
	private String username;
	private String password;
	
	@Enumerated
	private GenderType gender;
	private LocalDate birthDate;
	private String email;
	
}
