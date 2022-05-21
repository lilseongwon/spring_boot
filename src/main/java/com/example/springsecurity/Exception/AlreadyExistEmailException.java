package com.example.springsecurity.Exception;

import com.example.springsecurity.service.AccountService;
import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AlreadyExistEmailException extends RuntimeException {
    private static final String MESSAGE = "이미 등록된 이메일 입니다.";
    public AlreadyExistEmailException () {
        super(MESSAGE);
    }
}