/*
 * Copyright (c) 2020.  ahianzhang@gmail.com
 */

package com.ahianzhang.auth.application;


import com.ahianzhang.auth.domain.account.Account;
import com.ahianzhang.auth.domain.account.AccountService;
import com.ahianzhang.auth.domain.client.Client;
import com.ahianzhang.auth.domain.client.ClientService;
import com.ahianzhang.auth.domain.token.*;
import com.ahianzhang.auth.domain.token.constant.GrantType;
import com.ahianzhang.auth.domain.token.util.JwtUtils;
import com.ahianzhang.auth.infrastructure.exception.BizException;
import com.ahianzhang.auth.infrastructure.exception.ErrorMessageEnum;
import com.ahianzhang.auth.infrastructure.facade.AccountThreadLocal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 2020/9/18 3:19 PM
 **/
@Service
@RefreshScope
public class TokenManagerApplicationServiceImpl implements TokenManagerApplicationService {
    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
    private final ClientService clientService;
    private final AccountService accountService;
    private final TokenService tokenService;
    private final RefreshTokenTypeService refreshTokenTypeService;
    private final StringRedisTemplate stringRedisTemplate;

    @Value("${xuanji.oauth.access-token.expire-time:7200}")
    private Integer accessTokenExpiresIn;
    @Value("${xuanji.oauth.refresh-token.absolute-expiration:604800}")
    private Integer refreshTokenAbsoluteExpiresIn;
    @Value("${xuanji.oauth.refresh-token.sliding-expiration:0}")
    private Integer refreshTokenSlidingExpiresIn;

    public TokenManagerApplicationServiceImpl(ClientService clientService, TokenService tokenService, AccountService accountService, RefreshTokenTypeService refreshTokenTypeService, StringRedisTemplate stringRedisTemplate) {
        this.clientService = clientService;
        this.tokenService = tokenService;
        this.accountService = accountService;
        this.refreshTokenTypeService = refreshTokenTypeService;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public AccessToken issueToken(String grantType, String clientId, String scope, String id, String clientSecret, String username, String password, String refreshToken) {
        // 校验授权类型
        GrantType type = GrantType.typeOf(grantType);
        // 查询 client
        Client client = clientService.findClientByClientId(clientId);
        String userId = null;
        if (client == null) {
            throw new BizException(ErrorMessageEnum.INVALID_CLIENT);
        }

        if (!client.isEnabled()) {
            throw new BizException(ErrorMessageEnum.DISABLED_CLIENT);
        }
        if (GrantType.PASSWORD.equals(type)) {
            // 查询用户
            Account account = accountService.findByUsernamePassword(username, password);
            if (Objects.isNull(account)) {
                throw new BizException(ErrorMessageEnum.INVALID_USER);
            }
            AccountThreadLocal.set(account);
            userId = account.getUserId();
            refreshToken = UUID.randomUUID().toString().replaceAll("-", "");

        }
        if (GrantType.REFRESH_TOKEN.equals(type)) {
            if (refreshToken == null) {
                throw new IllegalArgumentException("refresh_token 为空！");
            }
            RefreshToken refreshTokenType = refreshTokenTypeService.findByRefreshToken(refreshToken);
            if (refreshTokenSlidingExpiresIn > 0) {
                if (refreshTokenType.isOutOfDate(refreshTokenSlidingExpiresIn)) {
                    throw new BizException("refresh token 失效");
                }
            }
            userId = refreshTokenType.getUserId();
            refreshTokenTypeService.updateLastUsedTime(refreshTokenType.getSid());

        }

        Token token = tokenService.createAccessToken(accessTokenExpiresIn, refreshTokenAbsoluteExpiresIn);
        String jwt = generateSelfContainedToken(userId, username, clientId, scope, accessTokenExpiresIn);
        token.setJwt(jwt);
        token.setRefreshToken(refreshToken);
        token.setClientId(clientId);
        token.setUserId(userId);
        token.setUserName(username);
        token.setClientId(clientId);

        tokenService.saveToken(token);

        if (GrantType.PASSWORD.equals(type)) {
            refreshTokenTypeService.saveRefreshTypeToken(new RefreshToken(clientId, userId, refreshToken, refreshTokenAbsoluteExpiresIn));
        }
        stringRedisTemplate.opsForValue().set("oat:" + token.getAccessToken(), jwt, accessTokenExpiresIn, TimeUnit.SECONDS);
        log.info(token.toString());
        return new AccessToken(token);

    }

    @Override
    public Boolean revokeToken(String accessToken) {
        return tokenService.revokeToken(accessToken);
    }

    private String generateSelfContainedToken(String userId, String username, String clientId, String scope, Integer ttlSecond) {
        return JwtUtils.createJsonWebToken(userId, username, clientId, scope, ttlSecond);
    }

}
