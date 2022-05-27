package com.example.springsecurity.service;

import com.example.springsecurity.Exception.AlreadyExistEmailException;
import com.example.springsecurity.domain.Account;
import com.example.springsecurity.domain.Sex;
import com.example.springsecurity.dto.*;
import com.example.springsecurity.dto.repository.AccountRepository;
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

    @Transactional
    public void createUser(AccountForm form) {
        String accountId = form.getAccountId();
        Optional<Account> account = accountRepository.findByAccountId(accountId);
        if (account.isEmpty()) {
            accountRepository.save(
                    Account.builder()
                            .accountId(form.getAccountId())
                            .password(passwordEncoder.encode(form.getPassword()))
                            .email(form.getEmail())
                            .name(form.getName())
                            .student_id(form.getStudent_id())
                            .sex(form.getSex())
                            .build());
        } else {
            throw new AlreadyExistEmailException();
        }
    }

    @Transactional(readOnly = true)
    public UserListResponse searchAllDesc() {

        List<ResponseDto> userList = accountRepository.findAllByOrderByIdDesc()
                .stream()
                .map(this::accountBuilder)
                .collect(Collectors.toList());

        return new UserListResponse(userList);
    }
    private ResponseDto accountBuilder(Account account) {
        return ResponseDto.builder()
                .accountId(account.getAccountId())
                .email(account.getEmail())
                .name(account.getName())
                .studentId(account.getStudent_id())
                .sex(account.getSex())
                .build();
    }

    @Transactional
    public String logIn(LoginRequest loginrequest) {
        Account account = accountRepository.findByAccountId(loginrequest.getAccountId())
                .orElseThrow(RuntimeException::new);

        if (account == null) {
            throw new AlreadyExistEmailException();
        }

        if (passwordEncoder.matches(loginrequest.getPassword(), account.getPassword())) {
            return "login succeeded";
        }

        if (!passwordEncoder.matches(loginrequest.getPassword(), account.getPassword())) {
            return "password is not matched";
        }

        return "Hello";
    }
    @Transactional
    public void delete (Long id){
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 계정이 없습니다. id=" + id));
        accountRepository.delete(account);
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));


        account.update(requestDto.getAccountId(), passwordEncoder.encode(requestDto.getPassword()), requestDto.getEmail(),
                requestDto.getName(), requestDto.getStudent_id(), requestDto.getSex());

        return id;
    }
}
