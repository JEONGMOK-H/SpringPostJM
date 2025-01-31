package com.example.post.service.post;

import com.example.post.model.posts.CreatePostDto;
import com.example.post.model.posts.EditPostDto;
import com.example.post.model.posts.Post;
import com.example.post.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Post getPostById(Long postId){
        log.info("-- 서비스 : id로 post 찾기");
        Post findPost = postRepository.findById(postId).orElse(null);

        if(findPost.equals(null)){
            log.info("-- 알림 : findPost == null");
        }

        return findPost;
    }

    @Override
    public void increaseViews(Long postId){
        log.info("-- 서비스 : 조회수 1 증가");
        Post findPost = postRepository.findById(postId).orElse(null);
        findPost.increaseViews();
    }

    @Override
    public void removePost(Long postId){
        log.info("-- 서비스 : 글 삭제");
        postRepository.deleteById(postId);
    }

    public void editPost(Post findPost, EditPostDto editPostDto){
        log.info("-- 서비스 : 글 수정");
        findPost.setTitle(editPostDto.getTitle());
        findPost.setContent(editPostDto.getContent());
    }


}
