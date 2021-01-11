/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ahianzhang.auth.api.vo;

import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 2020/9/15 6:09 PM
 **/
public final class CreateTokenRequest {
    @NotNull
    private final String grantType;
    private final String username;
    private final String password;
    private final String clientId;
    private final String clientSecret;
    @NotNull
    private final String scopes;

    public CreateTokenRequest(@RequestParam("grant_type") String grantType,
                              @RequestParam(value = "username", required = false) String username,
                              @RequestParam(value = "password", required = false) String password,
                              @RequestParam("client_id") String clientId,
                              @RequestParam(value = "client_secret", required = false) String clientSecret,
                              @RequestParam(value = "scope", required = false) String scope) {
        this.grantType = grantType;
        this.username = username;
        this.password = password;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.scopes = scope;
    }




}
