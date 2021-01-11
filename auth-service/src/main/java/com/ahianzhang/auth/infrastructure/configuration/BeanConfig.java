/*
 * Copyright (c) 2020.  ahianzhang@gmail.com
 */

package com.ahianzhang.auth.infrastructure.configuration;


import com.ahianzhang.auth.AuthServer;
import com.ahianzhang.auth.domain.account.AccountRepository;
import com.ahianzhang.auth.domain.account.AccountService;
import com.ahianzhang.auth.domain.account.AccountServiceImpl;
import com.ahianzhang.auth.domain.client.ClientRepository;
import com.ahianzhang.auth.domain.client.ClientService;
import com.ahianzhang.auth.domain.client.DefaultClientServiceImpl;
import com.ahianzhang.auth.domain.token.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 2020/9/15 11:26 AM
 **/
@Configuration
@ComponentScan(basePackageClasses = AuthServer.class)
public class BeanConfig {

    @Bean
    ClientService clientService(final ClientRepository clientRepository){
        return new DefaultClientServiceImpl(clientRepository);
    }
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @Bean
    TokenService tokenService(final TokenRepository tokenRepository){
        return new TokenServiceImpl(tokenRepository);
    }
    @Bean
    AccountService accountService(@Qualifier("accountFacade")final AccountRepository accountRepository){
        return new AccountServiceImpl( accountRepository);
    }

    @Bean
    RefreshTokenTypeService refreshTokenTypeService(final RefreshTokenTypeRepository refreshTokenTypeRepository){
        return new RefreshTokenTypeServiceImpl(refreshTokenTypeRepository);
    }

}
