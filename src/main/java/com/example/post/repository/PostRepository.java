package com.example.post.repository;

import java.util.List;

import org.springframework.ui.Model;

import com.example.post.model.posts.Post;


public interface PostRepository {
	// 글 등록
	void savePost(Post post);
	
	// 글 전체 조회
	List<Post> findAllPosts();
	
	// 아이디로 글 조회
	Post findPostByid(Long postId);
	
	// 글수정
	void updatePost(Post post);
	
	// 글 삭제
	void removePost(Long postId, String password);

}
