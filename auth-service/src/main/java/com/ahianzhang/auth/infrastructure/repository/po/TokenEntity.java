/*
 * Copyright (c) 2020.  ahianzhang@gmail.com
 */

package com.ahianzhang.auth.infrastructure.repository.po;

import com.ahianzhang.auth.domain.account.Account;
import com.ahianzhang.auth.domain.token.Token;
import com.ahianzhang.auth.infrastructure.facade.AccountThreadLocal;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 2020/9/16 10:24 PM
 **/
@Table("oauth_access_token")
public class TokenEntity {
    @Id
    @Column("sid")
    private  Long id;
    /**
     * 应用禁用后根据 clientId 触发 token 删除事件
     */
    private  String clientId;
    /**
     * 用户登出后根据 userId 触发 token 删除事件
     */
    private  String userId;
    private  String jwt;
    private  String accessToken;
    private  String refreshToken;
    @Column("expires_in")
    private  Integer accessTokenExpiresIn;
    @Transient
    private Integer refreshTokenExpireIn;
    @CreatedDate
    private DateTime createTime;

    private String createUser;

    public TokenEntity() {
    }

    public TokenEntity(Token token) {
        this.id = token.getId();
        this.accessToken = token.getAccessToken();
        this.jwt = token.getJwt();
        this.refreshToken = token.getRefreshToken();
        this.accessTokenExpiresIn = token.getAccessTokenExpiresIn();
        this.clientId = token.getClientId();
        this.userId = token.getUserId();
        this.refreshTokenExpireIn = token.getRefreshTokenExpiresIn();
        this.createUser = token.getUserName();
    }

    public Token toToken(){
        return new Token(id,jwt,accessToken,refreshToken,accessTokenExpiresIn);
    }
    public void setCreateUser(){
        Account account = AccountThreadLocal.get();
        this.createUser = account.getUsername();
    }

    public Long getId() {
        return id;
    }

    public String getClientId() {
        return clientId;
    }

    public String getUserId() {
        return userId;
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

    public Integer getAccessTokenExpiresIn() {
        return accessTokenExpiresIn;
    }

    public Integer getRefreshTokenExpireIn() {
        return refreshTokenExpireIn;
    }

    public DateTime getCreateTime() {
        return createTime;
    }

    public String getCreateUser() {
        return createUser;
    }
}
