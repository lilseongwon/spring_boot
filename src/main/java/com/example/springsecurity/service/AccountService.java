package com.example.springsecurity.service;

import com.example.springsecurity.domain.Account;
import com.example.springsecurity.dto.AccountForm;
import com.example.springsecurity.dto.LoginRequest;
import com.example.springsecurity.dto.ResponseDto;
import com.example.springsecurity.dto.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;

    @Transactional
    public void createUser(AccountForm form) {
        accountRepository.save(
            Account.builder()
                    .username(form.getUsername())
                    .password(passwordEncoder.encode(form.getPassword()))
                    .email(form.getEmail())
                    .age(form.getAge())
                    .role(form.getRole())
                    .sex(form.getSex())
                    .build());
    }

    @Transactional(readOnly = true)
    public List<ResponseDto> searchAllDesc() {
        return accountRepository.findAllByOrderByIdDesc().stream()
                .map(ResponseDto::new)
                .collect(Collectors.toList());
    }
    @Transactional
    public String login(LoginRequest loginrequest) {
        Account account = accountRepository.findByUsername(loginrequest.getUsername())
                .orElseThrow(RuntimeException::new);

        if(account==null){
            return "email does not exist";
        }
        if(passwordEncoder.matches(loginrequest.getPassword(), account.getPassword())) {
            return "login succeeded";
        }
        if(!passwordEncoder.matches(loginrequest.getPassword(), account.getPassword())) {
            return "password is not matched";
        }
        return "Hello";
    }
 /*   public void Login(AccountForm form) {
        Optional<Account> account = accountRepository.findByUsername(form.getUsername());

        if(!passwordEncoder.matches(form.getPassword(), account.getPassword())) {

            throw Exception
        }
    } */

}


