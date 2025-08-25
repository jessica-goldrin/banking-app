package com.example.banking_app.service;

import com.example.banking_app.dto.*;
import com.example.banking_app.entity.*;
import com.example.banking_app.exception.*;
import com.example.banking_app.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public AccountResponse createAccount(AccountCreateRequest request) {
        Account account = new Account(request.getOwnerName(), request.getInitialBalance());
        Account saved = accountRepository.save(account);
        return new AccountResponse(saved.getId(), saved.getOwnerName(), saved.getBalance());
    }

    @Transactional
    public void transferFunds(TransferRequest request) {
        Account from = accountRepository.findById(request.getFromAccountId())
                .orElseThrow(() -> new NotFoundException("From account not found"));

        Account to = accountRepository.findById(request.getToAccountId())
                .orElseThrow(() -> new NotFoundException("To account not found"));

        if (from.getBalance().compareTo(request.getAmount()) < 0) {
            throw new InsufficientFundsException("Insufficient funds in account " + from.getId());
        }

        from.setBalance(from.getBalance().subtract(request.getAmount()));
        to.setBalance(to.getBalance().add(request.getAmount()));

        accountRepository.save(from);
        accountRepository.save(to);

        transactionRepository.save(new Transaction(from.getId(), to.getId(), request.getAmount()));
    }

    public List<TransactionResponse> getTransactionHistory(Long accountId) {
        if (!accountRepository.existsById(accountId)) {
            throw new NotFoundException("Account not found");
        }

        return transactionRepository.findByFromAccountIdOrToAccountId(accountId, accountId)
                .stream()
                .map(tx -> new TransactionResponse(
                        tx.getId(),
                        tx.getFromAccountId(),
                        tx.getToAccountId(),
                        tx.getAmount(),
                        tx.getTimestamp()
                ))
                .collect(Collectors.toList());
    }
}
