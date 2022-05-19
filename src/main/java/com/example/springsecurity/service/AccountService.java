package com.example.springsecurity.service;

import com.example.springsecurity.domain.Account;
import com.example.springsecurity.dto.AccountForm;
import com.example.springsecurity.dto.ResponseDto;
import com.example.springsecurity.dto.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.IllformedLocaleException;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public void createUser(AccountForm form) {
        Account account = form.toEntity();
        accountRepository.save(account);
    }
    public ResponseDto findById (Long id){
        Account entity = AccountRepository.findById(id)
                .orElseThrow(() -> new
IllformedLocaleException("해당 ~가 없습니다. id=" + id));
        return new ResponseDto(entity);
    }


}