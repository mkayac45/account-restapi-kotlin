package com.mkayacdev.account.service;

import com.mkayacdev.account.dto.AccountDto;
import com.mkayacdev.account.dto.AccountDtoConverter;
import com.mkayacdev.account.dto.CreateAccountRequest;
import com.mkayacdev.account.model.Account;
import com.mkayacdev.account.model.Customer;
import com.mkayacdev.account.model.Transaction;
import com.mkayacdev.account.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final TransactionService transactionService;
    private final AccountDtoConverter converter;

    public AccountService(AccountRepository accountRepository, CustomerService customerService,
                          TransactionService transactionService, TransactionService transactionService1, AccountDtoConverter converter) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.transactionService = transactionService1;
        this.converter = converter;
    }

    public AccountDto createAccount(CreateAccountRequest createAccountRequest){
        Customer customer = customerService.findCustomerById(createAccountRequest.getCustomerId());

        Account account = new Account(customer,
                createAccountRequest.getInitialCredit(),
                LocalDateTime.now()
                );
        if(createAccountRequest.getInitialCredit().compareTo(BigDecimal.ZERO)> 0){
            Transaction transaction = transactionService.initiateMoney(account,createAccountRequest);
            account.getTransaction().add(transaction);
        }

        return converter.convert(accountRepository.save(account));

    }
}
