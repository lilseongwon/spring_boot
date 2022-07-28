package com.example.springsecurity.domain.domain.repository;

import com.example.springsecurity.domain.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long>{
    List<Account> findAllByOrderByIdDesc();

    Optional<Account> findByAccountId(String accountId);

}