package com.example.post.model.posts;

import com.example.post.model.users.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreatePostDto {

    private String title;
    private String content;
    private User user;

}
