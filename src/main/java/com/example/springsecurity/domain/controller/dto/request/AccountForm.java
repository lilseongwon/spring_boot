package com.example.springsecurity.domain.controller.dto.request;


import com.example.springsecurity.domain.domain.Sex;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class AccountForm {
    @NotBlank(message = "ID는 빈 값일 수 없습니다")
    private String accountId;

    @NotBlank(message = "비밀번호는 빈 값일 수 없습니다")
    private String password;

    @NotBlank(message = "이메일은 빈 값일 수 없습니다")
    @Email(message = "올바른 형식의 이메일 주소어야 합니다")
    private String email;
    
    @NotBlank(message = "이름은 빈 값일 수 없습니다")
    private String name;

    @NotBlank(message = "학번은 빈 값일 수 없습니다")
    private String student_id;

    private Sex sex;  //Enum 검증하려면 커스텀 어노테이션 필요
}
