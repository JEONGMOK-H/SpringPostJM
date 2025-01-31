package com.example.post.model.posts;

import com.example.post.model.users.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EditPostDto {
    @NotBlank(message = "제목은 필수 입력 사항 입니다.")
    @Size(min = 2, max = 100, message = "제목은 2자 이상, 100 이하입니다.")
    private String title;

    @NotBlank(message = "내용은 필수 입력 사항입니다.")
    @Size(min = 2,message = "내용을 입력해 주세요")
    private String content;
}
