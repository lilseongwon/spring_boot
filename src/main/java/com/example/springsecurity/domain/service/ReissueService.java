package com.example.springsecurity.domain.service;

import com.example.springsecurity.domain.domain.RefreshToken;
import com.example.springsecurity.domain.controller.dto.Response.TokenResponse;
import com.example.springsecurity.domain.domain.repository.RefreshTokenRepository;
import com.example.springsecurity.global.exception.InvalidRefreshTokenException;
import com.example.springsecurity.global.exception.RefreshTokenNotFoundException;
import com.example.springsecurity.global.security.jwt.JwtProperties;
import com.example.springsecurity.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ReissueService implements UserReissueService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperties jwtProperties;


    @Override
    @Transactional
    public TokenResponse userReissue(String refreshToken) {
        return reIssue(refreshToken);
    }

    private TokenResponse reIssue(String refreshToken) {
        String parseToken = jwtTokenProvider.parseToken(refreshToken);
        if (parseToken == null) {
            throw InvalidRefreshTokenException.EXCEPTION;
        }

        RefreshToken redisRefreshToken = refreshTokenRepository.findByToken(parseToken)
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);


        TokenResponse tokens = jwtTokenProvider.generateTokens(redisRefreshToken.getAccountId());

        redisRefreshToken.updateToken(tokens.getRefreshToken(), jwtProperties.getRefreshExp() * 1000);

        return TokenResponse.builder()
                .accessToken(tokens.getAccessToken())
                .refreshToken(tokens.getRefreshToken())
                .build();
    }
}