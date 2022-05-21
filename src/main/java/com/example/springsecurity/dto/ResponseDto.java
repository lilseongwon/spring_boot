package com.example.springsecurity.dto;

import com.example.springsecurity.domain.Account;
import com.example.springsecurity.domain.Sex;
import lombok.Getter;

@Getter
public class ResponseDto {
    private String accountId;
    private String password;
    private String email;
    private String name;
    private String student_id;
    private Sex sex;

    public ResponseDto(Account entity){
        this.accountId = entity.getAccountId();
        this.password = entity.getPassword();
        this.email = entity.getEmail();
        this.name = entity.getName();
        this.student_id = entity.getStudent_id();
        this.sex = entity.getSex();
    }
}

