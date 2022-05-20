package com.example.springsecurity.dto.repository;

import com.example.springsecurity.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByOrderByIdDesc();

    Optional<Account> findByUsername(String Username);


}