package com.example.springsecurity.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException{ // 모든 예외의 최상위인 exception 을 상속받는 Runtimme~ 상속 받는다
    private final ErrorCode errorCode; //에러코드
}

