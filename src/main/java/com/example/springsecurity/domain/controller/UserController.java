package com.example.springsecurity.domain.controller;


import com.example.springsecurity.domain.controller.dto.Response.ResponseDto;
import com.example.springsecurity.domain.controller.dto.Response.TokenResponse;
import com.example.springsecurity.domain.controller.dto.Response.UserListResponse;
import com.example.springsecurity.domain.controller.dto.request.AccountForm;
import com.example.springsecurity.domain.controller.dto.request.LoginRequest;
import com.example.springsecurity.domain.controller.dto.request.PostsUpdateRequestDto;
import com.example.springsecurity.domain.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController{
    private final AccountService accountService;

    @GetMapping("search/{account-id}")
    public ResponseDto search(@PathVariable("account-id") Long id){
        return accountService.search(id);
    }

    @CrossOrigin("*")
    @GetMapping("/searchAll")
    public UserListResponse searchAllDesc(){
        return accountService.searchAllDesc();
    }
    @CrossOrigin("*")
    @PostMapping("/sign-up")
    public void createUser(@RequestBody @Valid AccountForm form){
        accountService.createUser(form);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest loginRequest){
        return accountService.login(loginRequest);
    }

    @PutMapping("/edit/{account-id}")
    public void update(@PathVariable("account-id") Long id, @RequestBody PostsUpdateRequestDto updateRequestDto){
        accountService.update(id, updateRequestDto);
    }
    @DeleteMapping("/delete/{account-id}")
    public void delete(@PathVariable("account-id") Long id){
        accountService.delete(id);
    }
}
