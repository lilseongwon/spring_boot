package com.example.springsecurity.domain.service;

import com.example.springsecurity.domain.domain.Account;
import com.example.springsecurity.domain.controller.dto.Response.ResponseDto;
import com.example.springsecurity.domain.controller.dto.Response.TokenResponse;
import com.example.springsecurity.domain.controller.dto.Response.UserListResponse;
import com.example.springsecurity.domain.controller.dto.request.AccountForm;
import com.example.springsecurity.domain.controller.dto.request.LoginRequest;
import com.example.springsecurity.domain.controller.dto.request.PostsUpdateRequestDto;
import com.example.springsecurity.domain.domain.repository.AccountRepository;
import com.example.springsecurity.domain.exception.AlreadyExistEmailException;
import com.example.springsecurity.domain.exception.AuthNotFoundException;
import com.example.springsecurity.global.exception.PasswordMismatchException;
import com.example.springsecurity.global.security.auth.AuthenticationFacade;
import com.example.springsecurity.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AccountService {
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationFacade authenticationFacade;


    public ResponseDto search(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> AuthNotFoundException.EXCEPTION);
        return ResponseDto.builder()
                .accountId(account.getAccountId())
                .name(account.getName())
                .email(account.getEmail())
                .sex(account.getSex())
                .studentId(account.getStudent_id())
                .build();
    }

    public void createUser(AccountForm form) {
        String accountId = form.getAccountId();
        Optional<Account> account = accountRepository.findByAccountId(accountId);
        if (account.isPresent())
            throw AlreadyExistEmailException.EXCEPTION;

        accountRepository.save( //?????? ???????????? NULL??? ???????????? ???????
                Account.builder() //???????????? ??? ????????????
                        .accountId(form.getAccountId())
                        .password(passwordEncoder.encode(form.getPassword()))
                        .email(form.getEmail())
                        .student_id(form.getStudent_id())
                        .name(form.getName())
                        .sex(form.getSex())
                        .build());

    }

    @Transactional(readOnly = true)
    public UserListResponse searchAllDesc() { //???????????? ????????? ?????????
        List<ResponseDto> userList = accountRepository.findAllByOrderByIdDesc()//?????????????????? id????????? ??? ????????????
                .stream()//????????? ?????? ??????
                .map(this::accountBuilder)//reponseDto?????? ??????????????? ??????
                .collect(Collectors.toList()); //????????? ????????? ?????????

        return new UserListResponse(userList); //list ????????????
    }

    private ResponseDto accountBuilder(Account account) //????????? ?????? ????????? private?????? ??????. ??????????????? entity
    {
        return ResponseDto.builder() //responseDto??? ?????? ????????????
                .accountId(account.getAccountId())
                .email(account.getEmail())
                .studentId(account.getStudent_id())
                .name(account.getName())
                .sex(account.getSex())
                .build();
    }

    @Transactional
    public TokenResponse login(LoginRequest loginRequest) {

        Account account = accountRepository.findByAccountId(loginRequest.getAccountId())
                .orElseThrow(() -> AuthNotFoundException.EXCEPTION);

        if (!passwordEncoder.matches(loginRequest.getPassword(), account.getPassword())) {
            throw PasswordMismatchException.EXCEPTION;
        }

        TokenResponse tokenResponse = jwtTokenProvider.generateTokens(loginRequest.getAccountId());

        return TokenResponse.builder()
                .accessToken(tokenResponse.getAccessToken())
                .refreshToken(tokenResponse.getRefreshToken())
                .build();
    }

    @Transactional
    public void update(PostsUpdateRequestDto requestDto) {
        Account account = authenticationFacade.getCurrentUser();
        account.update(passwordEncoder.encode(requestDto.getPassword()), requestDto.getEmail(), requestDto.getStudent_id()
                , requestDto.getName(), requestDto.getSex());
    }

    @Transactional
    public void delete(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> AuthNotFoundException.EXCEPTION);
        accountRepository.delete(account);
    }
}