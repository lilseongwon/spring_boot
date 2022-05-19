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

    private String username;
    private String password;
    private String email;
    private String age;
    private String role;
    private Sex sex;

    @Builder
    public Account(String username, String password, String email, String age, String role, Sex sex) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        this.role = role;
        this.sex = sex;
    }
}
