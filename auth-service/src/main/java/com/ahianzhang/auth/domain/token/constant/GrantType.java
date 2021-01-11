/*
 * Copyright (c) 2020.  ahianzhang@gmail.com
 */

package com.ahianzhang.auth.domain.token.constant;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 2020/9/7 1:46 PM
 **/
public enum  GrantType {
    /**
     * 密码模式
     */
    PASSWORD("password"),
    /**
     * 授权码模式
     */
//    AUTHORIZATION_CODE("authorization_code"),
    /**
     * 客户端模式
     */
//    CLIENT_CREDENTIALS("client_credentials"),
    /**
     * 刷新 token
     */
   REFRESH_TOKEN("refresh_token")
    ;
    public static GrantType typeOf(String type) {
        GrantType grantType = resolve(type);
        if (grantType == null) {
            throw new IllegalArgumentException("没有找到 [" + type + "]");
        } else {
            return grantType;
        }
    }
    public static GrantType resolve(String typeString) {
        GrantType[] grantTypes = values();
        for (GrantType grantType : grantTypes) {
            if (grantType.type.equalsIgnoreCase(typeString)) {
                return grantType;
            }
        }
        return null;
    }

    private final String type;
    GrantType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
