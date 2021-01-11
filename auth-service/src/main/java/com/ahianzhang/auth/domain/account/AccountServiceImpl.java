/*
 * Copyright (c) 2020.  ahianzhang@gmail.com
 */

package com.ahianzhang.auth.domain.account;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 2020/9/23 5:23 PM
 **/
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account findByUsernamePassword(String username, String password) {
        return accountRepository.findByUsernamePassword(username, password);
    }
}
