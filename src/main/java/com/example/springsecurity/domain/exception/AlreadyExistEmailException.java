package com.example.springsecurity.domain.exception;

import com.example.springsecurity.global.error.CustomException;
import com.example.springsecurity.global.error.ErrorCode;

public class AlreadyExistEmailException extends CustomException {
    public static final CustomException EXCEPTION =
            new AlreadyExistEmailException(); //CustomException타입으로 익셉션 생성

    private AlreadyExistEmailException() { //싱글톤 패턴이니까 접근 못하게 private
        super(ErrorCode.AUTH_EXISTS);
        //부모클래스의 멤버를 초기화하기 위해선, 당연히 부모클래스의 생성자를 호출해야할 것이다.
        //매개변수를 가지는 생성자기 때문에 super로 cutomException(부모클래스)의 에러코드를 원하는 에러로 초기화시킨다.
        //부가설명 -> super는 부모 클래스로부터 상속받은 필드/메소드를 자식 클래스에서 참조하는데 사용, 즉 CustomException에서 선언한 에러코드 필드 중 원하는 에러를 참조.
    }
}