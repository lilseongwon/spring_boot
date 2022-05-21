package com.example.springsecurity.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
// 다른 패키지에서 생성자 함부로 생성하지 마세요!
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id
    // SQL 에서 자동생성되도록 돕는 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountId;
    private String password;
    private String email;
    private String name;
    private String student_id;
    private Sex sex;

    @Builder
    public Account(String accountId, String password, String email, String name, String student_id, Sex sex) {
        this.accountId = accountId;
        this.password = password;
        this.email = email;
        this.name = name;
        this.student_id = student_id;
        this.sex = sex;
    }
}
