package com.example.springsecurity.controller;


import com.example.springsecurity.Exception.AlreadyExistEmailException;
import com.example.springsecurity.domain.Account;
import com.example.springsecurity.dto.*;
import com.example.springsecurity.dto.repository.AccountRepository;
import com.example.springsecurity.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
    @PutMapping("/edit/{account-id}")
    public Long update(@PathVariable("account-id") Long id, @RequestBody PostsUpdateRequestDto postsUpdateRequestDto){
        return accountService.update(id, postsUpdateRequestDto);
    }
}
