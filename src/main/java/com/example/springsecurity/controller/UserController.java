package com.example.springsecurity.controller;


import com.example.springsecurity.dto.*;
import com.example.springsecurity.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController{
    private final AccountService accountService;

    @CrossOrigin("*")
    @GetMapping("/search")
    public UserListResponse searchAllDesc(){
        return accountService.searchAllDesc();
    }
    @CrossOrigin("*")
    @PostMapping("/sign-up")
    public void createUser(@RequestBody @Valid AccountForm form){
        accountService.createUser(form);
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
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
