/*
 * Copyright (c) 2020.  ahianzhang@gmail.com
 */

package com.ahianzhang.auth.domain.token;

/**
 * @author ahianzhang
 */
public interface RefreshTokenTypeService {

    /**
     * @param refreshToken
     * @return
     */
    RefreshToken findByRefreshToken(String refreshToken);

    /**
     * @param refreshToken
     */
    void saveRefreshTypeToken(RefreshToken refreshToken);

    void updateLastUsedTime(Long sid);

}
