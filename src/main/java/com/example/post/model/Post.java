package com.example.post.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Post {
	private Long id;
	private String title;
	private String content;
	private String username;
	private String password;
	private int views;
	private LocalDateTime createTime;
	
	//조회수 증가
	
	public void incrementViews() {
		this.views++;
	}

}
