package com.example.springsecurity.dto;

import com.example.springsecurity.domain.Sex;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder //이거 왜 빌더 들어감?
public class ResponseDto {
    private final String accountId;
    private final String email;
    private final String studentId;
    private final String name;
    private final Sex sex;
}

