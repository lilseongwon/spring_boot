package com.example.springsecurity.dto;


import com.example.springsecurity.domain.Account;
import com.example.springsecurity.domain.Sex;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class AccountForm {
    @NotBlank(message = "공백 포함 불가")
    private String accountId;

    @NotBlank(message = "공백 포함 불가")
    private String password;

    @Email(message = "이메일 형식에 맞춰주세요")
    private String email;
    
    @NotBlank(message = "공백 포함 불가")
    private String name;

    @NotBlank(message = "공백 포함 불가")
    private String student_id;

    @NotBlank(message = "공백 포함 불가")
    private Sex sex;
}
