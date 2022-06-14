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

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice //여기서 에러가 터지면 ExceptionHandler 로
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class) //CustomException 클래스로 캐치
    public ResponseEntity<CustomErrorResponse> customExceptionHandling(CustomException e) { //ResponseEntity 로 반환
        final ErrorCode errorCode = e.getErrorCode(); //-> 에러코드 익셉션에서 받아오기
        return new ResponseEntity<>(
                CustomErrorResponse.builder() //빌더로 Response 생성
                        .status(errorCode.getStatus()) //status코드
                        .message(errorCode.getMessage()) //메세지 코드
                        .build(), //빌더 끝내고
                HttpStatus.valueOf(errorCode.getStatus()) //valueOf가 상태코드를 넘버객체로 반환
        );
    }

    @ExceptionHandler(BindException.class) //BindException 클래스로 캐치
    public ResponseEntity<BindErrorResponse> bindExceptionHandling(BindException e) { //밑에 리스폰스 있음.
        List<String> errorsList = new ArrayList<>(); //리스트 타입으로 에러리스트 선언(가변적인 ArrayList)

        for (FieldError error : e.getFieldErrors()) { //error 의 길이만큼 반복하여 메세지를 추가한다
            errorsList.add(error.getDefaultMessage()); //리스트(배열)에 메세지 넣어주기
        }
        BindErrorResponse errorResponse = BindErrorResponse.builder() //응답 빌드해서 상태매코드랑 메세지 받가
                .status(HttpStatus.BAD_REQUEST.value()) //정수값 받기(상태코드)
                .messages(errorsList) //에러리스트 메세지로 띄우기
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST); //리턴
    }

}
