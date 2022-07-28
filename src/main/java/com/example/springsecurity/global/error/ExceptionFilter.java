package com.example.springsecurity.global.error;

import com.example.springsecurity.global.error.ErrorResponse.CustomErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
//final을 위해 책정.
public class ExceptionFilter extends OncePerRequestFilter {
    // 한 요청에 대해서 필터를 한번만 실행 시켜주고 , 필터를 적용 시켜줘
    private final ObjectMapper objectMapper;
    // ObjcetMapper -> Json 형식으로 받으려고 쓴다
    @Override //http servlet 줌
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws IOException {
        try {
            filterChain.doFilter(request, response); //필터체인 돌림, 근데 매개변수로  왜 얘네들 쓰는거임?
        } catch (CustomException e) { //unchecked 에러나옴 sendErrorMessage는 밑에 있음
            sendErrorMessage(response, e.getErrorCode()); //
        } catch (Exception e) {
            logger.error(e);
            sendErrorMessage(response, ErrorCode.INTERNAL_SERVER_ERROR); //checked 에러
            //무슨 에러인지 몰라서 500으로 해놓는다.
        }
    }

    private void sendErrorMessage(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        //IOException(checked exception) 위임 - write쓰니까
        CustomErrorResponse customErrorResponse = CustomErrorResponse.builder() //응답 빌더로
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .build();
        //에러코드에서 상태코드랑 메세지 받아서 빌드.
        String errorResponseJson = objectMapper.writeValueAsString(customErrorResponse); //리스폰스 문자열로 직렬화

        response.setStatus(errorCode.getStatus()); //setStatus -> response(응답)의 상태코드 설정)
        response.setContentType("application/json"); //Content-Type으로 요청 또는 응답의 데이터가 어떤 형식인지 판단, = JSON 형식
        response.getWriter().write(errorResponseJson); //response(응답파일)에 에러를 쓴다...?
    } //write = 파일에 쓰는거
}