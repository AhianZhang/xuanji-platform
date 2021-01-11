/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ahianzhang.auth.infrastructure.repository;


import com.ahianzhang.auth.domain.token.Token;
import com.ahianzhang.auth.domain.token.TokenRepository;
import com.ahianzhang.auth.infrastructure.exception.BizException;
import com.ahianzhang.auth.infrastructure.repository.po.TokenEntity;
import org.springframework.stereotype.Component;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 2020/9/16 10:29 PM
 **/
@Component
public class DefaultTokenRepository implements TokenRepository {
    private final SpringDataJdbcTokenRepository tokenRepository;


    public DefaultTokenRepository(SpringDataJdbcTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public Token save(Token token) {
        TokenEntity tokenEntity = tokenRepository.save(new TokenEntity(token));
       return tokenEntity.toToken();

    }

    @Override
    public Boolean revokeToken(String accessToken) {
        long row = tokenRepository.deleteByAccessToken(accessToken);
        if (row == 0){
            throw new BizException("token 未找到");
        }
        return true;
    }

    @Override
    public void deleteExpireToken() {
        tokenRepository.deleteAllByExpireTime();
    }


}
