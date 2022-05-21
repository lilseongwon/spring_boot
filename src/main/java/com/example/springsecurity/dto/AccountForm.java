package com.example.springsecurity.dto;


import com.example.springsecurity.domain.Account;
import com.example.springsecurity.domain.Sex;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountForm {

    private String accountId;
    private String password;
    private String email;
    private String name;
    private String student_id;
    private Sex sex;


}
