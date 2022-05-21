package com.example.springsecurity.service;

import com.example.springsecurity.Exception.AlreadyExistEmailException;
import com.example.springsecurity.domain.Account;
import com.example.springsecurity.dto.AccountForm;
import com.example.springsecurity.dto.LoginRequest;
import com.example.springsecurity.dto.ResponseDto;
import com.example.springsecurity.dto.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.Null;
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
        String account_id  = form.getAccount_id();
        Optional<Account> account = accountRepository.findByAccount_id(account_id);
        if ( account.isEmpty() ) {
            accountRepository.save(
                    Account.builder()
                            .account_id(form.getAccount_id())
                            .email(form.getEmail())
                            .password(passwordEncoder.encode(form.getPassword()))
                            .name(form.getName())
                            .student_id(form.getStudent_id())
                            .sex(form.getSex())
                            .build());
        } else {
            throw new AlreadyExistEmailException();
        }
    }

    @Transactional(readOnly = true)
    public List<ResponseDto> searchAllDesc() {
        return accountRepository.findAllByOrderByIdDesc().stream()
                .map(ResponseDto::new)
                .collect(Collectors.toList());
    }
    @Transactional
    public String login(LoginRequest loginrequest) {
        Account account = accountRepository.findByAccount_id(loginrequest.getAccount_id())
                .orElseThrow(RuntimeException::new);

        if(account== null){
            return "id does not exist";
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


