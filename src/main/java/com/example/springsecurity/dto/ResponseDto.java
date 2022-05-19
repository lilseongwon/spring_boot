package com.example.springsecurity.dto;

import com.example.springsecurity.domain.Account;
import com.example.springsecurity.domain.Sex;
import lombok.Getter;

@Getter
public class ResponseDto {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String age;
    private String role;
    private Sex sex;

    public ResponseDto(Account entity){
        this.id = getId();
        this.username = getUsername();
        this.password = getPassword();
        this.email = getEmail();
        this.age = getAge();
        this.role = getRole();
        this.sex = getSex();
    }
}
