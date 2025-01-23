package com.example.post.model.posts;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import com.example.post.model.users.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Entity

@Data
public class Post {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String content;
	
	@
	private User user;
	private int views;
	private LocalDateTime createTime;
	
	//조회수 증가
	
	public void incrementViews() {
		this.views++;
	}

}
