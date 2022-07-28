package com.example.springsecurity.global.error;

import com.example.springsecurity.global.error.ErrorResponse.BindErrorResponse;
import com.example.springsecurity.global.error.ErrorResponse.CustomErrorResponse;
import com.example.springsecurity.global.error.ErrorResponse.BindErrorResponse;
import com.example.springsecurity.global.error.ErrorResponse.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice //여기서 에러가 터지면 ExceptionHandler 로
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class) //CustomException 클래스로 캐치, 근데 value = 를 넣으면 달라지나? 아님 이걸 생략한걸까
    public ResponseEntity<CustomErrorResponse> customExceptionHandling(CustomException e) { //ResponseEntity 타입으로 메서드 만들기. 커스텀익셉션이 파라미터
        final ErrorCode errorCode = e.getErrorCode(); //-> 에러코드 익셉션에서 받아오기
        return new ResponseEntity<>( //왜 여기서 new를 넣을까...?
                CustomErrorResponse.builder() //빌더로 ResponseEntity 방식으로 예외 Response 생성
                        .status(errorCode.getStatus()) //status코드
                        .message(errorCode.getMessage()) //메세지 코드
                        .build(), //빌더 끝내고
                HttpStatus.valueOf(errorCode.getStatus()) //valueOf가 상태코드를 넘버객체로 반환
        );
    }
    @ExceptionHandler(BindException.class) // BindException -> @valid 와 연관
    public ResponseEntity<?> bindExceptionHandling(BindException e) {
        Map<String, String> errorsList = new HashMap<>(); // error 담는 리스트 key - value 형태로 저장되는 배열 : Map
        for (FieldError error : e.getFieldErrors()) { // error 의 길이만큼 반복하여 메세지를 추가한다
            errorsList.put(error.getField(), error.getDefaultMessage()); // 변수 이름 : 메세지 형식으로 추가
        }

        return new ResponseEntity<>(errorsList, HttpStatus.BAD_REQUEST);
    }

}
