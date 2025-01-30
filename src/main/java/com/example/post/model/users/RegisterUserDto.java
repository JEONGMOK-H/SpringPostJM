package com.example.post.model.users;

import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterUserDto {

    private String username;
    private String password;
    private String name;

    @Enumerated
    private GenderType genderType;
    private LocalDate birthDate;
    private String email;

}
