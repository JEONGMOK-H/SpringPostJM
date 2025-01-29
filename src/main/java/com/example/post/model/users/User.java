package com.example.post.model.users;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String password;
    private String name;

    @Enumerated
    private GenderType genderType;
    private LocalDate birthDate;
    private String email;
}
