/*
 * Copyright (c) 2020.  ahianzhang@gmail.com
 */

package com.ahianzhang.auth.infrastructure.repository.po;

import com.ahianzhang.auth.domain.token.RefreshToken;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 2020/9/25 3:47 PM
 **/
@Table("oauth_refresh_token")
public class RefreshTokenEntity {
    @Id
    @Column("sid")
    private Long id;
    private String clientId;
    private String userId;
    private String refreshToken;
    private Integer expiresIn;
    @CreatedDate
    private DateTime createTime;
    @LastModifiedDate
    private DateTime lastUsedTime;

    public RefreshTokenEntity() {
    }

    public RefreshTokenEntity(String clientId, String userId, String refreshToken, Integer expiresIn) {
        this.clientId = clientId;
        this.userId = userId;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
    }

    public RefreshToken toRefreshToken(){
        return new RefreshToken(
               this.id, clientId,userId,refreshToken,expiresIn,createTime,lastUsedTime
        );

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

    public String getRefreshToken() {
        return refreshToken;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public DateTime getCreateTime() {
        return createTime;
    }

    public DateTime getLastUsedTime() {
        return lastUsedTime;
    }
}
