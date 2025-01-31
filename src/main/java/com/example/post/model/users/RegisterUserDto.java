package com.example.post.model.users;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterUserDto {
    @Size(min = 4, max = 15, message = "아이디는 4자 이상 15이하 입니다.")
    private String username;
    @Size(min = 4, max = 15, message = "비밀번호는 4자이상 15이상 입니다.")
    private String password;
    @NotBlank(message = "이름은 필수 입력입니다.")
    private String name;

    @Enumerated
    private GenderType genderType;
    @Past(message = "날짜를 확인해주세요.")
    private LocalDate birthDate;
    @Email(message = "이메일을 확인해 주세요.")
    private String email;

}
