package com.example.springsecurity.global.security.auth;

import com.example.springsecurity.domain.domain.repository.AccountRepository;
import com.example.springsecurity.domain.exception.AuthNotFoundException;
import com.example.springsecurity.global.exception.PasswordMismatchException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {
        return new AuthDetails(
                accountRepository.findByAccountId(accountId)
                        .orElseThrow(() -> AuthNotFoundException.EXCEPTION)
        );
    }


}