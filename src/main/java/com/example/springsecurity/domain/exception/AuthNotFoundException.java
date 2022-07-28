package com.example.springsecurity.domain.exception;

import com.example.springsecurity.global.error.CustomException;
import com.example.springsecurity.global.error.ErrorCode;

public class AuthNotFoundException extends CustomException {
    public static final CustomException EXCEPTION =
            new AuthNotFoundException();

    private AuthNotFoundException(){
        super(ErrorCode.AUTH_NOT_FOUND);
    }
}
