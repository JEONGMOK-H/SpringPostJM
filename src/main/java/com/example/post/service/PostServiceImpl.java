package com.example.post.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.post.model.posts.Post;
import com.example.post.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Transactional(readOnly = true)	// 데이터를 읽기 전용으로 가져온다
@Slf4j
@RequiredArgsConstructor // 의존성 주입
@Service
public class PostServiceImpl implements PostService {
	
	// PostService 객체생성 시점에 스프링 컨테이너가 자동으로 의존성 주입 DI Injection
	private final PostRepository postRepository ; // 의존성 주입 : final 꼭 있어야하고 final은 꼭 초기화 필
	
	
	// 글저장
	@Transactional
	@Override
	public Post savePost(Post post) {
		log.info("서비스 : SAVE POST");
		post.setCreateTime(LocalDateTime.now());
		postRepository.save(post);
		return post;
	}
	
	// 글 전체조회
	@Override
	public List<Post> getAllPosts() {
		return postRepository.findAll();
	}
	
	// 아이디로 글조회
	@Transactional
	@Override
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
		log.info("incresement test : {}", post.getViews());
		return post;
		
		
	}
	
	// 글 삭제
	@Transactional
	@Override
	public void removePost(Long postId) {
		//글 조회
		
		Optional<Post> findPost = postRepository.findById(postId);
		
		Post post = findPost.orElseThrow(
				() -> new IllegalArgumentException("게시글없음"));
		
		postRepository.delete(post);
		
		
//		postRepository.deleteById(postId);
		
		
	}
	
	// 글 삭제
	public void editPost(Long postId) {
		//글 조회
		Optional<Post> findPost = postRepository.findById(postId);
				
		Post post = findPost.orElseThrow(
				() -> new IllegalArgumentException("게시글없음"));
		log.info("edit post : {}" , post);
		
	}

	
	

}
