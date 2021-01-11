/*
 * Copyright (c) 2020.  ahianzhang@gmail.com
 */

package com.ahianzhang.auth.api.vo.client;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 2020/9/22 5:21 PM
 **/
public class CreateClientResponse {
    /**
     * JsonInclude.Include.NON_NULL 当属性为空时，不向前端展示
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String clientId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String clientSecret;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorMessage;

    public CreateClientResponse() {
    }

    public CreateClientResponse(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public CreateClientResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
