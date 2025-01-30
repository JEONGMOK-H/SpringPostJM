package com.example.post.service.post;

import com.example.post.model.posts.CreatePostDto;
import com.example.post.model.posts.Post;
import com.example.post.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public void createPost(CreatePostDto createPostDto){
        log.info("-- 서비스 : 글 작성 시작");
        Post createPost = new Post();
        createPost.setTitle(createPostDto.getTitle());
        createPost.setContent(createPostDto.getContent());
        createPost.setUser(createPostDto.getUser());
        createPost.setCreateTime(LocalDateTime.now());

        postRepository.save(createPost);


    }

    @Override
    public List<Post> getAllPosts() {
        log.info("-- 서비스 : 전체 글 목록조회");
        List<Post> posts = postRepository.findAll();
        return posts;
    }
}
