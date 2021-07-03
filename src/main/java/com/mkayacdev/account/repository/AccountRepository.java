package com.mkayacdev.account.repository;

import com.mkayacdev.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,String> {
}
