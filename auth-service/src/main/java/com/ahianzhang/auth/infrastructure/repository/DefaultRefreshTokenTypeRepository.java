/*
 * Copyright (c) 2020.  ahianzhang@gmail.com
 */

package com.ahianzhang.auth.infrastructure.repository;

import com.ahianzhang.auth.domain.token.RefreshToken;
import com.ahianzhang.auth.domain.token.RefreshTokenTypeRepository;
import com.ahianzhang.auth.infrastructure.repository.po.RefreshTokenEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 2020/9/27 3:51 PM
 **/
@Component
public class DefaultRefreshTokenTypeRepository implements RefreshTokenTypeRepository {
    private final SpringDataJdbcRefreshTokenRepository refreshTokenRepository;

    public DefaultRefreshTokenTypeRepository(SpringDataJdbcRefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public RefreshToken findByRefreshToken(String refreshToken) {
        Optional<RefreshTokenEntity> refreshTokenEntity =
                refreshTokenRepository.findByRefreshToken(refreshToken);
        return refreshTokenEntity.map(RefreshTokenEntity::toRefreshToken).orElse(null);
    }

    @Override
    public void updateLastUsedTime(Long sid) {
        refreshTokenRepository.updateLastUsedTimeById(sid);
    }

    @Override
    public void saveRefreshTokenType(RefreshToken refreshToken) {
        RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity(refreshToken.getClientId(), refreshToken.getUserId(), refreshToken.getRefreshToken(), refreshToken.getExpiresIn());
        refreshTokenRepository.save(refreshTokenEntity);
    }


}
