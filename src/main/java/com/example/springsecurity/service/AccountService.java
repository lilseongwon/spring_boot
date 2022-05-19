package com.example.springsecurity.service;

import com.example.springsecurity.domain.Account;
import com.example.springsecurity.dto.AccountForm;
import com.example.springsecurity.dto.ResponseDto;
import com.example.springsecurity.dto.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.IllformedLocaleException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public void createUser(AccountForm form) {
        Account account = form.toEntity();
        accountRepository.save(account);
    }
    @Transactional(readOnly = true)
    public List<ResponseDto> searchAllDesc() {
        return accountRepository.findAllByOrderByIdDesc().stream()
                .map(ResponseDto::new)
                .collect(Collectors.toList());
    }


}


