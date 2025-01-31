package com.example.post.service.post;

import com.example.post.model.posts.CreatePostDto;
import com.example.post.model.posts.EditPostDto;
import com.example.post.model.posts.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    public void createPost(CreatePostDto createPostDto);

    public List<Post> getAllPosts();

    public Post getPostById(Long postId);

    public void increaseViews(Long postId);

    public void removePost(Long postId);

    public void editPost(Post findPost, EditPostDto editPostDto);
}
