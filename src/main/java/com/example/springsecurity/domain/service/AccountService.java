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
    public UserListResponse searchAllDesc() { //멘토에게 질문이 시급함
        List<ResponseDto> userList = accountRepository.findAllByOrderByIdDesc()//유저리스트에 id찾은거 다 때려박기
                .stream()//람다식 사용 명시
                .map(this::accountBuilder)//reponseDto값을 배열에다가 넣기
                .collect(Collectors.toList()); //리스트 형태로 합치기

        return new UserListResponse(userList); //list 리턴하기
    }

    private ResponseDto accountBuilder(Account account) //딴데서 접근 못하게 private으로 설정. 매개변수는 entity
    {
        return ResponseDto.builder() //responseDto에 정보 때려박기
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

        if (!passwordEncoder.matches(loginRequest.getPassword(), loginRequest.getPassword())) {
            throw PasswordMismatchException.EXCEPTION;
        }

        TokenResponse tokenResponse = jwtTokenProvider.generateTokens(loginRequest.getAccountId());

        return TokenResponse.builder()
                .accessToken(tokenResponse.getAccessToken())
                .refreshToken(tokenResponse.getRefreshToken())
                .build();
    }

    @Transactional
    public void update(Long id, PostsUpdateRequestDto requestDto) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> AuthNotFoundException.EXCEPTION);
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