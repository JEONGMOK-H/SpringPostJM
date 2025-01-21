package com.example.post.model.users;

import java.time.LocalDate;

import lombok.Data;

@Data
public class User {
	private Long id; // PK Column 회원정보(data)를 구분하는 아이디 값
	private String name;
	private String username;
	private String password;
	private GenderType gender;
	private LocalDate birthDate;
	private String email;
}
