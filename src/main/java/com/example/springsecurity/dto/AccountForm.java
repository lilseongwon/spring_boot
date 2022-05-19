package com.example.springsecurity.dto;


import com.example.springsecurity.domain.Account;
import com.example.springsecurity.domain.Sex;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountForm {

    private String username;
    private String password;
    private String email;
    private String age;
    private String role;
    private Sex sex;

    public Account toEntity() {
        return Account.builder()
                .username(username)
                .password(password)
                .email(email)
                .age(age)
                .role(role)
                .sex(sex)
                .build();
    }
}