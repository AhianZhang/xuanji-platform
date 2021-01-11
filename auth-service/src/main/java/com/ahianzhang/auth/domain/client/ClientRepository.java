/*
 * Copyright (c) 2020.  ahianzhang@gmail.com
 */

package com.ahianzhang.auth.domain.client;

import java.util.Optional;

/**
 * client 端口
 * 次适配器 driven
 * @author ahianzhang
 */
public interface ClientRepository {
    /**
     * 保存
     * @param client 应用
     */
    void save(Client client);

    /**
     * 根据 id 查询
     * @param id 唯一标识
     * @return client
     */
    Optional<Client> findById(Integer id);

    /**
     * 根据 clientId 查询
     * @param clientId 客户端标识
     * @return client
     */
    Optional<Client> findByClientId(String clientId);


    /**
     * 根据 clientName 查询
     * @param clientName 客户端标识
     * @return client
     */
    Optional<Client> findByClientName(String clientName);
}
