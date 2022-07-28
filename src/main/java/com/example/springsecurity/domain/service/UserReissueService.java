package com.example.springsecurity.domain.service;

import com.example.springsecurity.domain.controller.dto.Response.TokenResponse;

public interface UserReissueService {

    TokenResponse userReissue(String RefreshToken);
}