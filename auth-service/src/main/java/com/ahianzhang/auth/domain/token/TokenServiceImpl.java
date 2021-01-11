/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ahianzhang.auth.domain.token;

import java.util.Objects;
import java.util.Optional;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 2020/9/16 12:17 PM
 **/
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;

    public TokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public Token createAccessToken(Integer accessTokenExpiresIn,Integer refreshTokenExpiresIn) {
        Token token = new Token(accessTokenExpiresIn,refreshTokenExpiresIn);
        token.generateAccessToken();
        return token;
    }


    @Override
    public void saveToken(Token token) {
        token.verify();
        tokenRepository.save(token);
    }

    @Override
    public Boolean revokeToken(String accessToken) {
        return tokenRepository.revokeToken(accessToken);
    }

    @Override
    public void deleteExpireToken() {
        tokenRepository.deleteExpireToken();
    }

}
