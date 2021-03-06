package com.example.springsecurity.domain.domain;

import com.example.springsecurity.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60, nullable = false)
    String accountId;

    @Column(length = 64, nullable = false)
    String password;
    @Column(length = 60, nullable = false)
    String email;
    @Column(length = 50, nullable = false)
    String student_id;
    @Column(length = 50, nullable = false)
    String name;
    @Column(length = 6, nullable = false)
    Sex sex;

    @Builder
    public Account(String accountId, String password, String email, String student_id, String name, Sex sex){
        this.accountId = accountId;
        this.password = password;
        this.email = email;
        this.student_id = student_id;
        this.name = name;
        this.sex = sex;
    }

    public void update(String password, String email, String student_id, String name, Sex sex){
        this.password = password;
        this.email = email;
        this.student_id = student_id;
        this.name = name;
        this.sex = sex;
    }
}
