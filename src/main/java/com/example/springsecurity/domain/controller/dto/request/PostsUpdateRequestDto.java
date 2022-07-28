package com.example.springsecurity.domain.controller.dto.request;

import com.example.springsecurity.domain.domain.Sex;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String password;
    private String email;
    private String name;
    private String student_id;
    private Sex sex;

}

