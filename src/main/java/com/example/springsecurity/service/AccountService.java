package com.example.springsecurity.service;

import com.example.springsecurity.domain.Account;
import com.example.springsecurity.dto.*;
import com.example.springsecurity.dto.repository.AccountRepository;
import com.example.springsecurity.exception.AlreadyExistEmailException;
import com.example.springsecurity.exception.AuthNotFoundException;
import com.example.springsecurity.exception.PasswordMismatchException;
import com.example.springsecurity.global.error.CustomException;
import com.example.springsecurity.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AccountService{
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;

    public void createUser(AccountForm form){
        String accountId = form.getAccountId();
        Optional<Account> account = accountRepository.findByAccountId(accountId);
        if (account.isPresent())
            throw AlreadyExistEmailException.EXCEPTION;

        accountRepository.save( //이거 자동으로 NULL값 확인하지 않음?
                Account.builder() //빌더타입 더 공부하기
                        .accountId(form.getAccountId())
                        .password(passwordEncoder.encode(form.getPassword()))
                        .email(form.getEmail())
                        .student_id(form.getStudent_id())
                        .name(form.getName())
                        .sex(form.getSex())
                        .build());

    }
    @Transactional(readOnly = true)
    public UserListResponse searchAllDesc(){ //멘토에게 질문이 시급함
        List<ResponseDto> userList = accountRepository.findAllByOrderByIdDesc()
                .stream()
                .map(this::accountBuilder)
                .collect(Collectors.toList());

        return new UserListResponse(userList);
    }
    private ResponseDto accountBuilder(Account account)
    {
        return ResponseDto.builder()
                .accountId(account.getAccountId())
                .email(account.getEmail())
                .studentId(account.getStudent_id())
                .name(account.getName())
                .sex(account.getSex())
                .build();
    }

    @Transactional
    public String login(LoginRequest loginRequest){
        Account account = accountRepository.findByAccountId(loginRequest.getAccountId()) //여기선 왜 optional 안됨?
                .orElseThrow(()-> AuthNotFoundException.EXCEPTION);
        if(!passwordEncoder.matches(loginRequest.getPassword(), account.getPassword())){
            throw PasswordMismatchException.EXCEPTION;
        }
        else
            return "login succeeded";
    }

    @Transactional
    public void update(Long id, PostsUpdateRequestDto requestDto){
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> AuthNotFoundException.EXCEPTION);
        account.update(passwordEncoder.encode(requestDto.getPassword()), requestDto.getEmail(), requestDto.getStudent_id()
        , requestDto.getName(), requestDto.getSex());

    }

    @Transactional
    public void delete(Long id){
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> AuthNotFoundException.EXCEPTION);
        accountRepository.delete(account);
    }
}