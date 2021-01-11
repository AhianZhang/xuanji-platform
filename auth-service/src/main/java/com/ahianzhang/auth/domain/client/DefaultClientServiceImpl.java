/*
 * Copyright (c) 2020.  ahianzhang@gmail.com
 */

package com.ahianzhang.auth.domain.client;


import com.ahianzhang.auth.infrastructure.exception.BizException;

import java.util.Objects;
import java.util.Optional;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 2020/9/14 11:09 AM
 **/
public class DefaultClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public DefaultClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client create(Client client) {
        validateClient(client);
        Client existedClient = checkIfClientRegistered(client.getClientName());

        // 已存在同名的应用，返回空值
        if (!Objects.isNull(existedClient)) {
            return null;
        } else {

            client.generateClientId();
            client.generateClientSecret();
            client.setStatus(true);
            clientRepository.save(client);
            return client;
        }

    }

    @Override
    public Client findClientById(Integer id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        return clientOptional.orElseGet(Client::new);
    }

    @Override
    public Client findClientByClientId(String clientId) {
        Optional<Client> client = clientRepository.findByClientId(clientId);
        return client.orElseGet(Client::new);
    }

    @Override
    public void delete(Integer id) {
    }

    @Override
    public void update(Client client) {

    }

    @Override
    public Client findByClientName(String clientName) {
        Optional<Client> client = clientRepository.findByClientName(clientName);
        return client.orElseGet(Client::new);
    }

    private void validateClient(Client client) {
        if (Objects.isNull(client)) {
            throw new BizException("client 为空");
        }
    }

    /**
     * @param clientName
     * @return true: 已创建过；false：未创建
     */
    private Client checkIfClientRegistered(String clientName) {
        Optional<Client> clientIfExist = clientRepository.findByClientName(clientName);

        return clientIfExist.orElse(null);
    }

}
