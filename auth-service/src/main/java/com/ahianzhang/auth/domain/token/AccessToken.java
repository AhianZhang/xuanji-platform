/*
 * Copyright (c) 2020.  ahianzhang@gmail.com
 */

package com.ahianzhang.auth.domain.token;

/**
 * access token 值对象
 * @author ahianzhang
 * @version 1.0
 * @date 2020/9/10 5:19 PM
 **/
public class AccessToken {
    private String accessToken;
    private String refreshToken;
    private Integer expiresIn;
    private String tokenType = "bearer";

    public AccessToken(String accessToken, String refreshToken, Integer expiresIn) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
    }
    public AccessToken(Token token){
       this.accessToken = token.getAccessToken();
       this.refreshToken = token.getRefreshToken();
       this.expiresIn = token.getAccessTokenExpiresIn();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public String getTokenType() {
        return tokenType;
    }
}
