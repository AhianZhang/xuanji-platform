/*
 * Copyright (c) 2020.  ahianzhang@gmail.com
 */

package com.ahianzhang.auth.domain.token;

public interface RefreshTokenTypeRepository {
    RefreshToken findByRefreshToken(String refreshToken);

    void updateLastUsedTime(Long sid);

    void saveRefreshTokenType(RefreshToken refreshToken);
}
