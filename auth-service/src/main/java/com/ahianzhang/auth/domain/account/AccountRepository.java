/*
 * Copyright (c) 2020.  ahianzhang@gmail.com
 */

package com.ahianzhang.auth.domain.account;

/**
 * @author ahianzhang
 */
public interface AccountRepository {
    /**
     * 根据用户名密码查用户信息
     * @param username 用户名
     * @param password 密码
     * @return 账户信息
     */
    public Account findByUsernamePassword(String username,String password);
}
