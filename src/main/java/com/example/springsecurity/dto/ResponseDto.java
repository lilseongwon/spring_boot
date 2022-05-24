package com.example.springsecurity.dto;

import com.example.springsecurity.domain.Sex;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseDto {
    private final String accountId;
    private final String password;
    private final String email;
    private final String name;
    private final String studentId;
    private final Sex sex;
}

