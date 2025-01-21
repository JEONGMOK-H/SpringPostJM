package com.example.post.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.post.model.posts.Post;
import com.example.post.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {
	
	// PostService 객체생성 시점에 스프링 컨테이너가 자동으로 의존성 주입 DI Injection
	private final PostRepository postRepository ; // 의존성 주입 : final 꼭 있어야하고 final은 꼭 초기화 필
	
	// 글저장
	public Post savePost(Post post) {
		log.info("서비스 시작 : SAVE POST");
		post.setCreateTime(LocalDateTime.now());
		postRepository.savePost(post);
		
		
		return post;
	}
	
	// 글 전체조회
	public List<Post> getAllPosts() {
		
		
		return postRepository.findAllPosts();
	}
	
	// 아이디로 글조회
	public Post getPostById(Long postId) {
		Post findPost = postRepository.findPostByid(postId);
		findPost.incrementViews();
		
		
		return findPost;
	}
	
	public void removePost(Long postId,String password) {
		//글 조회
		Post findPost = postRepository.findPostByid(postId);
		if(findPost.getPassword().equals(password)) {
			postRepository.removePost(postId,password);
			
		}
		
		
	}
	

}
