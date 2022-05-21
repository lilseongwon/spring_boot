package com.example.springsecurity.dto;

import com.example.springsecurity.domain.Account;
import com.example.springsecurity.domain.Sex;
import lombok.Getter;

@Getter
public class ResponseDto {
    private String account_id;
    private String email;
    private String password;
    private String name;
    private String student_id;
    private Sex sex;

    public ResponseDto(Account entity){
        this.account_id = entity.getAccount_id();
        this.password = entity.getEmail();
        this.email = entity.getPassword();
        this.name = entity.getName();
        this.student_id = entity.getStudent_id();
        this.sex = entity.getSex();
    }
}

