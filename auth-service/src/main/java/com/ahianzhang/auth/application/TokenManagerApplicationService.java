/*
 * Copyright (c) 2020.  ahianzhang@gmail.com
 */

package com.ahianzhang.auth.application;

import com.ahianzhang.auth.domain.token.AccessToken;



/**
 * @author ahianzhang
 */
public interface TokenManagerApplicationService {

    /**
     * @param grantType
     * @param clientId
     * @param scope
     * @param id
     * @param clientSecret
     * @param username
     * @param password
     * @param refreshToken
     * @return
     */
    AccessToken issueToken(String grantType, String clientId, String scope, String id, String clientSecret, String username, String password, String refreshToken);

    Boolean revokeToken(String accessToken);
}
