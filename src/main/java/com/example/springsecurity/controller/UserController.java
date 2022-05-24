package com.example.springsecurity.controller;


import com.example.springsecurity.Exception.AlreadyExistEmailException;
import com.example.springsecurity.domain.Account;
import com.example.springsecurity.dto.AccountForm;
import com.example.springsecurity.dto.LoginRequest;
import com.example.springsecurity.dto.ResponseDto;
import com.example.springsecurity.dto.UserListResponse;
import com.example.springsecurity.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final AccountService accountService;

    @GetMapping("/search")
    public UserListResponse searchAllDesc() {
        return accountService.searchAllDesc();
    }

    @PostMapping("/sign")
    public void createUser(@RequestBody AccountForm form) {
        accountService.createUser(form);
    }

    @PostMapping("/login")
    public void Login(@Valid @RequestBody LoginRequest loginrequest){
        accountService.logIn(loginrequest);
    }
    @DeleteMapping("/delete/{account-id}")
    public void delete(@PathVariable("account-id") Long id) {
        accountService.delete(id);
    }

}
