package com.example.springsecurity.exception;

import com.example.springsecurity.global.error.CustomException;
import com.example.springsecurity.global.error.ErrorCode;

public class AlreadyExistEmailException extends CustomException {
    public static final CustomException EXCEPTION =
            new AlreadyExistEmailException();

    private AlreadyExistEmailException() {
        super(ErrorCode.AUTH_EXISTS);
        //부모클래스의 멤버를 초기화하기 위해선, 당연히 부모클래스의 생성자를 호출해야할 것이다.
        //매개변수를 가지는 생성자기 때문에 super로 cutomException(부모클래스)의 에러코드를 원하는 에러로 초기화시킨다.
    }
}