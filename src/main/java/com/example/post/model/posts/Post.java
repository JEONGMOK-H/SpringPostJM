package com.example.post.model.posts;

import com.example.post.model.users.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @Lob                                // 정확히 뭔지모르겠음 물어봐야함
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)  // n+1 문제 방지
    @JoinColumn(name = "user_id")       // 외래키 컬럼의 이름 지정
    private User user;

    private int views;
    private LocalDateTime createTime;

    public void increaseViews(){
        views++;
    }

}
