package com.example.springsecurity.global.error.ErrorResponse;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class BindErrorResponse {

    private final int status; //상태코드
    private final List<String> messages; //메세지

}