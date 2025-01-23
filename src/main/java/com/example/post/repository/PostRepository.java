package com.example.post.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.post.model.posts.Post;




public interface PostRepository extends JpaRepository<Post, Long>{
	
	public void deleteById(Long id); 
	
	public Optional<Post> findById(Long id);
	
	
	
}
