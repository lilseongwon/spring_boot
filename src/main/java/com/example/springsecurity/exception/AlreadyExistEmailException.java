package com.example.springsecurity.exception;

import com.example.springsecurity.global.error.CustomException;
import com.example.springsecurity.global.error.ErrorCode;

public class AlreadyExistEmailException extends CustomException {
    public static final CustomException EXCEPTION =
            new AlreadyExistEmailException();

    private AlreadyExistEmailException() {
        super(ErrorCode.AUTH_EXISTS);
    }
}