package com.example.springsecurity.dto;

import com.example.springsecurity.domain.Account;
import com.example.springsecurity.domain.Sex;
import lombok.Getter;

@Getter
public class ResponseDto {
    private String username;
    private String password;
    private String email;
    private String role;
    private Sex sex;

    public ResponseDto(Account entity){
        this.username = entity.getUsername();
        this.password = entity.getPassword();
        this.email = entity.getEmail();
        this.role = entity.getRole();
        this.sex = entity.getSex();
    }
}

