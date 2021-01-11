/*
 * Copyright (c) 2020.  ahianzhang@gmail.com
 */

package com.ahianzhang.auth.infrastructure.facade;


import com.ahianzhang.auth.domain.account.Account;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 2020/9/18 6:20 PM
 **/
public class AccountThreadLocal {
    private AccountThreadLocal() {
    }

    private static final ThreadLocal<Account> accountThreadLocal = new ThreadLocal<>();

    public static void set(Account account){
        accountThreadLocal.set(account);
    }
    public static Account get(){
        return accountThreadLocal.get();
    }
}
