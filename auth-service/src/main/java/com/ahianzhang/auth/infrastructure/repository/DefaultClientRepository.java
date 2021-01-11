/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ahianzhang.auth.infrastructure.repository;

import com.ahianzhang.auth.domain.client.Client;
import com.ahianzhang.auth.domain.client.ClientRepository;
import com.ahianzhang.auth.infrastructure.repository.po.ClientEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * @author ahianzhang
 * @version 1.0
 * @date 2020/9/14 11:10 AM
 **/
@Component
public class DefaultClientRepository implements ClientRepository {


    private final SpringDataJdbcClientRepository jdbcClientRepository;

    public DefaultClientRepository( final SpringDataJdbcClientRepository jdbcClientRepository) {
        this.jdbcClientRepository = jdbcClientRepository;
    }

    @Override
    public void save(final Client client) {
         jdbcClientRepository.save(new ClientEntity(client));
    }

    @Override
    public Optional<Client> findById(Integer id) {
        Optional<ClientEntity> clientEntity = jdbcClientRepository.findById(id);
        return clientEntity.map(ClientEntity::toClient);
    }

    @Override
    public Optional<Client> findByClientId(String clientId) {
        Optional<ClientEntity> clientEntity = jdbcClientRepository.findByClientId(clientId);
        return clientEntity.map(ClientEntity::toClient);
    }

    @Override
    public Optional<Client> findByClientName(String clientName) {
        Optional<ClientEntity> clientEntity = jdbcClientRepository.findByClientName(clientName);
        return clientEntity.map(ClientEntity::toClient);
    }
}
