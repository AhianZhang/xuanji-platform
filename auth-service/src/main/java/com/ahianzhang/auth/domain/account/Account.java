/*
 * Copyright (c) 2020.  ahianzhang@gmail.com
 */

package com.ahianzhang.auth.domain.account;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 2020/9/15 11:30 PM
 **/
public class Account {
    private String userId;
    private String username;
    private String password;
    private String roles;

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRoles() {
        return roles;
    }

    public Account() {
    }

    public Account(String userId, String username, String password, String roles) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}

