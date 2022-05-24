package com.example.springsecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class UserListResponse {
    private final List<ResponseDto> userLists;
}
