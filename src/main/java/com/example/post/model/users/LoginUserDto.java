package com.example.post.model.users;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LoginUserDto {
    @NotBlank(message = "아이디를 입력하세요.")
    @Size(min = 2,message = "아이디를 확인하세요")
    private String username;
    @NotBlank(message = "비밀번호를 입력하세요.")
    @Size(min = 2,message = "비밀번호를 확인하세요.")
    private String password;

}
