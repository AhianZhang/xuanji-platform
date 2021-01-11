/*
 * Copyright (c) 2020.  ahianzhang@gmail.com
 */

package com.ahianzhang.auth.domain.client;

import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 2020/9/7 4:15 PM
 **/
public class Client {

    private Integer id;
    private String clientId;
    private String clientSecret;
    private String clientName;
    private String redirectUri;
    private String scopes;
    private String grantType;
    private String ipWhitelist;
    private String createUser;
    private DateTime createTime;
    private Boolean status;
    public Client() {
    }

    public Client(String clientName, String redirectUri, String scopes, String grantType, String ipWhitelist) {
        this.clientName = clientName;
        this.redirectUri = redirectUri;
        this.scopes = scopes;
        this.grantType = grantType;
        this.ipWhitelist = ipWhitelist;
    }

    public Client(Integer id, String clientId, String clientSecret, String clientName, String redirectUri, String scopes, String grantType, String ipWhitelist, String createUser, DateTime createTime, Boolean status) {
        this.id = id;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.clientName = clientName;
        this.redirectUri = redirectUri;
        this.scopes = scopes;
        this.grantType = grantType;
        this.ipWhitelist = ipWhitelist;
        this.createUser = createUser;
        this.createTime = createTime;
        this.status = status;
    }

    public void generateClientId() {
        this.clientId = RandomStringUtils.randomAlphanumeric(16);

    }

    public void generateClientSecret() {
        this.clientSecret = RandomStringUtils.randomAlphanumeric(32);
    }

    public Integer getId() {
        return id;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getClientName() {
        return clientName;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public String getScopes() {
        return scopes;
    }

    public String getGrantType() {
        return grantType;
    }

    public String getIpWhitelist() {
        return ipWhitelist;
    }

    public String getCreateUser() {
        return createUser;
    }

    public DateTime getCreateTime() {
        return createTime;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    public boolean isEnabled(){
       return status;
    }
}
