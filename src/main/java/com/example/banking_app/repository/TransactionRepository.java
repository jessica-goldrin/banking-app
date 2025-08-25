package com.example.banking_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.banking_app.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByFromAccountIdOrToAccountId(Long fromAccountId, Long toAccountId);
}
