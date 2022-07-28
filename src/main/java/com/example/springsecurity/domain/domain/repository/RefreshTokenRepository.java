package com.example.springsecurity.domain.domain.repository;

import com.example.springsecurity.domain.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {

    Optional<RefreshToken> findByToken(String RefreshToken);
}