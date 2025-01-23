package com.example.post.service;

import java.util.List;

import com.example.post.model.posts.Post;

public interface PostService {
	
	public Post savePost(Post post);
	
	public List<Post> getAllPosts();
	
	public Post getPostById(Long postId);
	
	public void removePost(Long postId);

	public void editPost(Long postId);

}
