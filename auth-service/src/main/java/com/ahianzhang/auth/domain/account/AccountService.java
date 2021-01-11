/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ahianzhang.auth.domain.account;

/**
 * 用户认证抽象接口
 * @author ahianzhang
 */
public interface AccountService{
    /**
     * 根据用户名密码查用户信息
     * @param username 用户名
     * @param password 密码
     * @return 账户信息
     */
    public Account findByUsernamePassword(String username,String password);
}
