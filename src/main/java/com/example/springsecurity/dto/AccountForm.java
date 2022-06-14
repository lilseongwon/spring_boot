package com.example.springsecurity.dto;


import com.example.springsecurity.domain.Account;
import com.example.springsecurity.domain.Sex;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class AccountForm {
    private String accountId;
    @NotNull
    private String password;
    private String email;
    private String name;
    private String student_id;
    private Sex sex;
}
