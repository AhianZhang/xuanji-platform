/*
 * Copyright (c) 2020.  ahianzhang@gmail.com
 */

package com.ahianzhang.auth.infrastructure.repository.po;

import com.ahianzhang.auth.domain.client.Client;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 2020/9/16 9:41 PM
 **/
@Table("registered_client")
public class ClientEntity {
    @Id
    @Column("sid")
    private Integer id;
    private String clientId;
    private String clientSecret;
    private String clientName;
    private String redirectUri;
    private String scopes;
    private String grantType;
    private String ipWhitelist;
    private Boolean status;
    @CreatedBy
    private String createUser;
    @CreatedDate
    private DateTime createTime;

    public ClientEntity() {
    }

    public ClientEntity(Client client) {
      this.clientId = client.getClientId();
      this.clientSecret = client.getClientSecret();
      this.clientName = client.getClientName();
      this.redirectUri = client.getRedirectUri();
      this.scopes = client.getScopes();
      this.grantType = client.getGrantType();
      this.status = client.getStatus();
      this.ipWhitelist = client.getIpWhitelist();
    }
    public Client toClient(){

       return new Client(id,clientId,clientSecret,clientName,redirectUri,scopes,grantType,ipWhitelist,createUser,createTime,status);

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
}
