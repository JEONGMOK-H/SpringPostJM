package com.example.post.service.post;

import com.example.post.model.posts.CreatePostDto;
import com.example.post.model.posts.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    public void createPost(CreatePostDto createPostDto);

    public List<Post> getAllPosts();
}
