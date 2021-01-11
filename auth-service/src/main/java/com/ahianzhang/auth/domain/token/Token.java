/*
 * Copyright (c) 2020.  ahianzhang@gmail.com
 */

package com.ahianzhang.auth.domain.token;

import java.util.UUID;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 2020/9/7 4:29 PM
 **/

public class Token {
    private Long id;
    private String jwt;
    private String accessToken;
    private String refreshToken;
    private Integer accessTokenExpiresIn;
    private Integer refreshTokenExpiresIn;
    private String clientId;
    private String userId;
    private String userName;
    public Token(Integer expiresIn) {
        this.accessTokenExpiresIn = expiresIn;
    }

    public Token(Integer accessTokenExpiresIn, Integer refreshTokenExpiresIn) {
        this.accessTokenExpiresIn = accessTokenExpiresIn;
        this.refreshTokenExpiresIn = refreshTokenExpiresIn;
    }

    public Token() {
    }

    public Token(Long id, String jwt, String accessToken, String refreshToken, Integer expiresIn) {
        this.id = id;
        this.jwt = jwt;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accessTokenExpiresIn = expiresIn;
    }

    public Long getId() {
        return id;
    }
    public String getJwt() {
        return jwt;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getClientId() {
        return clientId;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getAccessTokenExpiresIn() {
        return accessTokenExpiresIn;
    }

    public Integer getRefreshTokenExpiresIn() {
        return refreshTokenExpiresIn;
    }

    public void generateAccessToken(){
       this.accessToken = UUID.randomUUID().toString().replaceAll("-","");
    }
//    public void generateRefreshToken(){
//       this.refreshToken = UUID.randomUUID().toString().replaceAll("-","");
//    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }


    public void verify(){
        if (clientId.isEmpty() || clientId == null){
            throw new NullPointerException("client 不能为空");
        }
    }
    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", jwt='" + jwt + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", accessTokenExpiresIn=" + accessTokenExpiresIn +
                ", refreshTokenExpiresIn=" + refreshTokenExpiresIn +
                ", clientId='" + clientId + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
