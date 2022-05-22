package com.example.springsecurity.controller;


import com.example.springsecurity.dto.AccountForm;
import com.example.springsecurity.dto.LoginRequest;
import com.example.springsecurity.dto.ResponseDto;
import com.example.springsecurity.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sign")
public class UserController {

    private final AccountService accountService;

    @GetMapping
    public List<ResponseDto> searchAllDesc() {
        return accountService.searchAllDesc();
    }

    @PostMapping
    public void createUser(@RequestBody AccountForm form) {
        accountService.createUser(form);
    }

    @PostMapping("/login")
    public void Login(@Valid @RequestBody LoginRequest loginrequest){
        accountService.logIn(loginrequest);
    }
}

 /*   @GetMapping("/fighting")
    public String ReturnJung(){
        return helloService.ReturnJung();
    }*/