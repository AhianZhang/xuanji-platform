/*
 * Copyright (c) 2020.  ahianzhang@gmail.com
 */

package com.ahianzhang.auth.domain.token;

import org.joda.time.DateTime;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 2020/9/25 3:21 PM
 **/
public class RefreshToken {

    private Long sid;
    private String clientId;
    private String userId;
    private String refreshToken;
    private Integer expiresIn;
    private DateTime createTime;
    private DateTime lastUsedTime;

    public RefreshToken(String clientId, String userId, String refreshToken, Integer expiresIn) {
        this.clientId = clientId;
        this.userId = userId;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
    }

    public RefreshToken(Long sid, String clientId, String userId, String refreshToken, Integer expiresIn, DateTime createTime, DateTime lastUsedTime) {
        this.sid = sid;
        this.clientId = clientId;
        this.userId = userId;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
        this.createTime = createTime;
        this.lastUsedTime = lastUsedTime;
    }

    public Long getSid() {
        return sid;
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

    public DateTime getCreateTime() {
        return createTime;
    }

    public DateTime getLastUsedTime() {
        return lastUsedTime;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    /**
     * 校验 refresh token 的有效性（是否失效）
     *
     * @param slidingExpiration 滑动过期时间
     * @return true: 失效,有效
     */
    public boolean isOutOfDate(Integer slidingExpiration) {

        if (!checkAbsoluteExpiration()) {
            return checkSlidingExpiration(slidingExpiration);
        }
        return true;
    }


    public void verify() {
        if (clientId == null || clientId.isEmpty()) {
            throw new NullPointerException("client 不能为空");
        }
    }

    /**
     * 校验绝对过期时间
     *
     * @return true: 已过期 | false: 未过期
     */
    private boolean checkAbsoluteExpiration() {
        return createTime.plusSeconds(expiresIn).isBefore(DateTime.now());
    }

    /**
     * 校验滑动过期时间
     *
     * @param slidingExpiration 滑动过期时间
     * @return true: 已过期 | false: 未过期
     */
    private boolean checkSlidingExpiration(Integer slidingExpiration) {
        return lastUsedTime.plusSeconds(slidingExpiration).isBefore(DateTime.now());
    }

    @Override
    public String toString() {
        return "RefreshToken{" +
                "clientId='" + clientId + '\'' +
                ", userId='" + userId + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", expiresIn=" + expiresIn +
                '}';
    }
}
