/*
 * Copyright (c) 2020.  ahianzhang@gmail.com
 */

package com.ahianzhang.auth.domain.token;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 2020/9/27 3:46 PM
 **/
public class RefreshTokenTypeServiceImpl implements RefreshTokenTypeService {

    private final RefreshTokenTypeRepository refreshTokenTypeRepository;

    public RefreshTokenTypeServiceImpl(RefreshTokenTypeRepository refreshTokenTypeRepository) {
        this.refreshTokenTypeRepository = refreshTokenTypeRepository;
    }


    @Override
    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenTypeRepository.findByRefreshToken(refreshToken);
    }

    @Override
    public void saveRefreshTypeToken(RefreshToken refreshToken) {

        refreshToken.verify();
        refreshTokenTypeRepository.saveRefreshTokenType(refreshToken);
    }

    @Override
    public void updateLastUsedTime(Long sid) {

        refreshTokenTypeRepository.updateLastUsedTime(sid);
    }
}
