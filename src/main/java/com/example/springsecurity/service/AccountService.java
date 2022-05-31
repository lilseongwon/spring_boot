package com.example.springsecurity.service;

import com.example.springsecurity.Exception.AlreadyExistEmailException;
import com.example.springsecurity.domain.Account;
import com.example.springsecurity.dto.*;
import com.example.springsecurity.dto.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.spec.PSSParameterSpec;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RequiredArgsConstructor
@Service
public class AccountService{
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;

    @Transactional
    public void createUser(AccountForm form){
        String accountId = form.getAccountId();
        Optional<Account> account = accountRepository.findByAccountId(accountId);
        if(account.isEmpty()){
            accountRepository.save(
                Account.builder()
                        .accountId(form.getAccountId())
                        .password(form.getPassword())
                        .email(form.getEmail())
                        .student_id(form.getStudent_id())
                        .name(form.getName())
                        .sex(form.getSex())
                        .build());
        }
        else
            throw new AlreadyExistEmailException();




    }
    @Transactional(readOnly = true)
    public UserListResponse searchAllDesc(){
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
                .name(account.getName())
                .studentId(account.getStudent_id())
                .sex(account.getSex())
                .build();
    }

    @Transactional
    public String login(LoginRequest loginRequest){
        Account account = accountRepository.findByAccountId(loginRequest.getAccountId())
                .orElseThrow(RuntimeException::new);
        if(passwordEncoder.matches(loginRequest.getPassword(), account.getPassword()))
        return "login succeeded";

        else
            return "password is not matched"; //else로 반환 수정
    }

    @Transactional
    public void update(Long id, PostsUpdateRequestDto requestDto){
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 계정이 없습니다."));
        account.update(requestDto.getAccountId(), requestDto.getPassword(), requestDto.getEmail(), requestDto.getStudent_id()
        , requestDto.getName(), requestDto.getSex());

    }

    @Transactional
    public void delete(Long id){
        Account account = accountRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 계정이 없습니다."));
        accountRepository.delete(account);
    }

}