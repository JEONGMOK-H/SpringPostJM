package com.example.post.model.users;

import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LoginUserDto {

    private String username;
    private String password;
    private String name;

    @Enumerated
    private GenderType genderType;
    private LocalDate birthDate;
    private String email;
}
