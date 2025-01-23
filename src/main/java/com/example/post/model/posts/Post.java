package com.example.post.model.posts;

import java.time.LocalDateTime;

import com.example.post.model.users.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Entity
@Data
public class Post {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	
	@Lob
	private String content;
	
	@ManyToOne(fetch = FetchType.LAZY) // ManyToOne은 n+1 문제 방지를 위해 LAZA 타입으로 하는게 좋다
	@JoinColumn(name="user_id") // ManyToOne에는 필수로 JoinColumn 지정(외래키 그 컬럼이름 지정)
	private User user;
	private int views;
	private LocalDateTime createTime;
	
	//조회수 증가
	
	public void incrementViews() {
		this.views++;
		log.info("views :{}" , this.views);
	}

}
