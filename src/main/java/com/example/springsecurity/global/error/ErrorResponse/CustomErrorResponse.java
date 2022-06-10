package com.example.springsecurity.global.error.ErrorResponse;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class CustomErrorResponse {

    private final int status;
    private final String message;

}