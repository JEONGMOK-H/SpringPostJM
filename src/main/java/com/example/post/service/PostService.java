package com.example.post.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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
		log.info("서비스 : SAVE POST");
		post.setCreateTime(LocalDateTime.now());
		postRepository.save(post);
		
		
		return post;
	}
	
	// 글 전체조회
	public List<Post> getAllPosts() {
		
		
		return postRepository.findAll();
	}
	
	// 아이디로 글조회
	public Post getPostById(Long postId) {
		Optional<Post> findPost = postRepository.findById(postId);
//		if(findPost.isPresent()) {
//			Post post = findPost.get();
//			post.incrementViews();
//			
//			return post;
//		}
//		new IllegalArgumentException("게시글 없음");
//		
//		return null;
		
		Post post = findPost.orElseThrow(
				() -> new IllegalArgumentException("게시글 없음")); // 값이 있으면 return 하고 null 이면 외예발생
		post.incrementViews();
		return post;
		
		
	}
	
	// 글 삭제
	public void removePost(Long postId) {
		//글 조회
		
		Optional<Post> findPost = postRepository.findById(postId);
		
		Post post = findPost.orElseThrow(
				() -> new IllegalArgumentException("게시글없음"));
		
		postRepository.delete(post);
		
		
//		postRepository.deleteById(postId);
		
		
	}

	
	

}
