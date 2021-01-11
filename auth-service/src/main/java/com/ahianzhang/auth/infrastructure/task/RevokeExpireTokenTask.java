/*
 * Copyright (c) 2020.  ahianzhang@gmail.com
 */

package com.ahianzhang.auth.infrastructure.task;

import com.ahianzhang.auth.domain.token.TokenService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 2020/9/20 6:39 PM
 **/
@EnableScheduling
@Configuration
public class RevokeExpireTokenTask {
    private final TokenService tokenService;

    public RevokeExpireTokenTask(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Scheduled(fixedDelay = 5 * 60 * 1000)
    public void deleteExpireTokens(){
        tokenService.deleteExpireToken();
    }
}
