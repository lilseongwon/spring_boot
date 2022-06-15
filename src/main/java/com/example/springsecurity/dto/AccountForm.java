package com.example.springsecurity.dto;


import com.example.springsecurity.domain.Account;
import com.example.springsecurity.domain.Sex;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class AccountForm {
    @NotEmpty(message = "공백을 허용하지 않습니다.")
    private String accountId;

    @NotEmpty(message = "공백을 허용하지 않습니다.")
    private String password;

    @NotEmpty(message = "공백을 허용하지 않습니다.")
    private String email;

    @NotEmpty(message = "공백을 허용하지 않습니다.")
    private String name;

    @NotEmpty(message = "공백을 허용하지 않습니다.")
    private String student_id;

    @NotEmpty(message = "공백을 허용하지 않습니다.")
    private Sex sex;
}
