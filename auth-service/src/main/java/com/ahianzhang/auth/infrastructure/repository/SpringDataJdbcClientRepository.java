/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ahianzhang.auth.infrastructure.repository;


import com.ahianzhang.auth.infrastructure.repository.po.ClientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 真实仓储类
 * @author ahianzhang
 * @version 1.0
 * @date 2020/9/14 11:14 AM
 **/
@Repository
public interface SpringDataJdbcClientRepository extends CrudRepository<ClientEntity,Integer> {
    /**
     * 根据 clientId 查询 client
     * @param clientId clientId
     * @return client
     */
    Optional<ClientEntity> findByClientId(String clientId);

    /**
     * @param clientName
     * @return
     */
    Optional<ClientEntity> findByClientName(String clientName);
}
