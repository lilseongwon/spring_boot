package com.example.springsecurity.global.security.auth;

import com.example.springsecurity.domain.domain.Account;
import com.example.springsecurity.domain.domain.repository.AccountRepository;
import com.example.springsecurity.domain.exception.AuthNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RequiredArgsConstructor
public class AuthenticationFacade {

    private final AccountRepository accountRepository;

    public Account getCurrentUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthDetails authDetails = (AuthDetails) authentication.getPrincipal();

        return accountRepository.findByAccountId(authDetails.getUsername())
                .orElseThrow(() -> AuthNotFoundException.EXCEPTION);
    }
}