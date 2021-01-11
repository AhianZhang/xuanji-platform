/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ahianzhang.auth.infrastructure.repository;

import com.ahianzhang.auth.infrastructure.repository.po.TokenEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ahianzhang
 */
@Repository
public interface SpringDataJdbcTokenRepository extends CrudRepository<TokenEntity,Integer> {


    @Modifying
    @Query("delete from oauth_access_token where oauth_access_token.access_token =:accessToken")
    long deleteByAccessToken(String accessToken);

    @Modifying
    @Query("DELETE FROM oauth_access_token WHERE DATE_ADD( create_time, INTERVAL expires_in SECOND )  < NOW()")
    void deleteAllByExpireTime();

}
