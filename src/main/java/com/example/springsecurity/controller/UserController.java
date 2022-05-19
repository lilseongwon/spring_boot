package com.example.springsecurity.controller;


import com.example.springsecurity.dto.AccountForm;
import com.example.springsecurity.dto.ResponseDto;
import com.example.springsecurity.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Sign")
public class UserController {

    private final AccountService accountService;

    @GetMapping
    public List<ResponseDto> searchAllDesc() {
        return accountService.searchAllDesc();
    }


    @PostMapping
    public void createUser(@Valid @RequestBody AccountForm form) {
        accountService.createUser(form);
    }

    @PostMapping("/Login")
    public void Login(@Valid @RequestBody AccountForm form){
        accountService.Login(form);
    }
}