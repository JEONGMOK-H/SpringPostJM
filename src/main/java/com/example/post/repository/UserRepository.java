package com.example.post.repository;

import com.example.post.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // username으로 유저 찾기
    public User findByUsername(String username);
}
