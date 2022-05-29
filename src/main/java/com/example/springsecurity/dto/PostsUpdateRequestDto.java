package com.example.springsecurity.dto;

import com.example.springsecurity.domain.Account;
import com.example.springsecurity.domain.Sex;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String accountId;
    private String password;
    private String email;
    private String name;
    private String student_id;
    private Sex sex;

    @Builder
    public PostsUpdateRequestDto(String accountId, String password, String email, String name, String student_id, Sex sex) {
        this.accountId = accountId;
        this.password =  password;
        this.email = email;
        this.name = name;
        this.student_id = student_id;
        this.sex = sex;
    }
}

