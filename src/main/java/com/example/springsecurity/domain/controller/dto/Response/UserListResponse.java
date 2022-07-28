package com.example.springsecurity.domain.controller.dto.Response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class UserListResponse {
    private final List<ResponseDto> userLists;
}
