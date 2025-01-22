package com.example.post.model.posts;

import java.time.LocalDateTime;

import com.example.post.model.users.User;

import lombok.Data;

@Data
public class Post {
	private Long id;
	private String title;
	private String content;
	private User user;
	private int views;
	private LocalDateTime createTime;
	
	//조회수 증가
	
	public void incrementViews() {
		this.views++;
	}

}
