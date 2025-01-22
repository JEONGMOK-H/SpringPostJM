package com.example.post.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import com.example.post.model.posts.Post;
import com.example.post.model.users.User;

@Repository
public class PostRepositoryImpl implements PostRepository {
	
	private static Map<Long, Post> posts = new HashMap<>();
	private static Long sequence = 0L;

	@Override
	public void savePost(Post post) {
		sequence++;
		post.setId(sequence);
		posts.put(sequence, post);
		
	}

	@Override
	public List<Post> findAllPosts() {
		
		List<Post> postList = new ArrayList<>(posts.values());
		
		return postList;
	}

	@Override
	public Post findPostByid(Long postId) { 
		return posts.get(postId);
	}

	@Override
	public void updatePost(Post post) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePost(Long postid, String password) {
		posts.remove(postid);
		
	}
	
	

}
