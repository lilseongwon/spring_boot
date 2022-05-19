package com.example.springsecurity.controller;

import com.example.springsecurity.dto.AccountForm;
import com.example.springsecurity.dto.ResponseDto;
import com.example.springsecurity.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class UserController {

    private final AccountService accountService;

    @GetMapping
    public ResponseDto findById(@PathVariable Long id)
    {
        return AccountService.findById(id);
    }


    @PostMapping
    public void createUser(@Valid @RequestBody AccountForm form) {
        accountService.createUser(form);
    }

}