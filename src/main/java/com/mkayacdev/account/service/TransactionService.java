package com.mkayacdev.account.service;

import com.mkayacdev.account.dto.CreateAccountRequest;
import com.mkayacdev.account.model.Account;
import com.mkayacdev.account.model.Transaction;
import com.mkayacdev.account.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionService {

    private Logger logger = LoggerFactory.getLogger(TransactionService.class);

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    protected Transaction initiateMoney(final Account account, CreateAccountRequest amount){

        return transactionRepository.save(
                new Transaction(amount,account)
        );
    }
}
