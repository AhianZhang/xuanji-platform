/*
 * Copyright (c) 2020.  ahianzhang@gmail.com
 */

package com.ahianzhang.auth.domain.token;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 2020/9/16 12:04 PM
 **/
public interface TokenService {

    /**
     * 生成 access token
     * @see <a href=https://www.oauth.com/oauth2-servers/access-tokens/password-grant>password grant</a>
     * @return access token
     */
    Token createAccessToken(Integer accessTokenExpiresIn,Integer refreshTokenExpiresIn);



    /**
     *
     * @param token
     */
    void saveToken(Token token);

    Boolean revokeToken(String accessToken);

    void deleteExpireToken();


}
