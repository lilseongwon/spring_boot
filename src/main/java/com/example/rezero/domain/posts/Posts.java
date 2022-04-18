package com.example.rezero.domain.posts; //클래스 위치

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ValueGenerationType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter //생성자(받아오는거, setter-주는거) get-set
@NoArgsConstructor //얘도 생성자, 구글링해서 종류 알기
@Entity //엔티티 만들어주는거
public class Posts { //public = 광역공격, private -단일공격
    @Id //???
    @GeneratedValue(strategy = GenerationType.IDENTITY) //데베한테 위임하는 전략 설정
    private Long id; //프라이빗으로

    @Column(length = 500, nullable = false)  //데베 칼럼의 최대 길이를 500으로 설정, null값 입력 불가
    private String title; //타이틀을 스트링형으로 선언

    @Column(columnDefinition = "TEXT", nullable = false) //
    private String content;

    private String author;//칼럼 없이 선언해도 칼럼에 넣어짐

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;//this??
        this.content = content;
        this.author = author;//위에거는 컬럼에만 있는거, 이거는 진짜 선언
    }//setter는 생성자 1개, 빌더는 생성자 여러개.
}

