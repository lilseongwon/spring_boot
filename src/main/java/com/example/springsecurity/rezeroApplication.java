package com.example.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class rezeroApplication {

    public static void main(String[] args) {
        SpringApplication.run(rezeroApplication.class, args);
    }
}
