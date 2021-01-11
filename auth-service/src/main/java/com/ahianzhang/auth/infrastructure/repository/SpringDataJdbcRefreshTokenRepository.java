/*
 * Copyright (c) 2020.  ahianzhang@gmail.com
 */

package com.ahianzhang.auth.infrastructure.repository;

import com.ahianzhang.auth.infrastructure.repository.po.RefreshTokenEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 2020/9/25 5:08 PM
 **/
@Repository
public interface SpringDataJdbcRefreshTokenRepository extends CrudRepository<RefreshTokenEntity,Long> {
    Optional<RefreshTokenEntity> findByRefreshToken(String refreshToken);

    @Modifying
    @Query("UPDATE oauth_refresh_token ort SET ort.last_used_time= NOW() WHERE ort.sid = :sid")
    void updateLastUsedTimeById(Long sid);
}
