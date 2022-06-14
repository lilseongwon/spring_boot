package com.example.springsecurity.exception;

import com.example.springsecurity.global.error.CustomException;
import com.example.springsecurity.global.error.ErrorCode;


public class PasswordMismatchException extends CustomException {
    public static final CustomException EXCEPTION =
            new PasswordMismatchException();

    private PasswordMismatchException(){
        super(ErrorCode.PASSWORD_MISMATCH);
    }
}
