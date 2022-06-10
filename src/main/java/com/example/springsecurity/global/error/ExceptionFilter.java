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

public class ExceptionFilter extends OncePerRequestFilter {
// 한 요청에 대해서 필터를 한번만 실행 시켜주고 , 필터를 적용 시켜줘
    private final ObjectMapper objectMapper;
// ObjcetMapper -> Json 형식으로 받으려고 쓴다
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (CustomException e) {
            sendErrorMessage(response, e.getErrorCode());
        } catch (Exception e) {
            sendErrorMessage(response, ErrorCode.INTERNAL_SERVER_ERROR);
            //무슨 에러인지 몰라서 500으로 해놓는다.
        }
    }

    private void sendErrorMessage(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        CustomErrorResponse customErrorResponse = CustomErrorResponse.builder()
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .build();
        //에러코드에서 상태코드랑 메세지 받아서 빌드.
        String errorResponseJson = objectMapper.writeValueAsString(customErrorResponse);

        response.setStatus(errorCode.getStatus());
        response.setContentType("application/json");
        response.getWriter().write(errorResponseJson);
    }
}