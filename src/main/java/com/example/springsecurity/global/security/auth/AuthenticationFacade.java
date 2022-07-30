package com.example.springsecurity.global.security.auth;

import com.example.springsecurity.domain.domain.Account;
import com.example.springsecurity.domain.domain.repository.AccountRepository;
import com.example.springsecurity.domain.exception.AuthNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AuthenticationFacade {
    private final AccountRepository accountRepository;

    public Account getCurrentUser() {


        String accountId = SecurityContextHolder.getContext().getAuthentication().getName();
        return accountRepository.findByAccountId(accountId)
                .orElseThrow(() -> AuthNotFoundException.EXCEPTION);
    }
}