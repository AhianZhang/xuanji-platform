/*
 * Copyright (c) 2020.  ahianzhang@gmail.com
 */

package com.ahianzhang.auth.infrastructure.facade;

import com.ahianzhang.auth.domain.account.Account;
import com.ahianzhang.auth.domain.account.AccountRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 防腐层
 * 用户名密码登录时使用 rest 方式进行认证
 * @author ahianzhang
 * @version 1.0
 * @date 2020/9/17 5:28 PM
 **/
@Service("accountFacade")
public class AccountFacade implements AccountRepository {
    @Value("${xuanji.account.url}")
    private String accountUrl;
    private final RestTemplate restTemplate;

    public AccountFacade(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Account findByUsernamePassword(String username, String password) {

       String loginUrl =accountUrl + "?account="+username+"&password="+password;
        String userId;
        String realName;
        String jsonStr = restTemplate.postForObject(loginUrl, null,String.class);
        try {
            JsonObject user = new JsonParser().parse(jsonStr).
                    getAsJsonObject().get("data").
                    getAsJsonObject().get("user")
                    .getAsJsonObject();

             userId = user.get("sid").getAsString();
             realName = user.get("realName").getAsString();
        }catch (Exception e){
            e.getMessage();
            return null;
        }

        Account account = new Account(userId,realName,null,null);
        return account;
    }
}
