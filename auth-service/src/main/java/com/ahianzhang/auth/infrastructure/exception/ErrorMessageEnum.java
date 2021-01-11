/*
 * Copyright (c) 2020.  ahianzhang@gmail.com
 */

package com.ahianzhang.auth.infrastructure.exception;


/**
 *
 * @see <a href="https://www.oauth.com/oauth2-servers/access-tokens/access-token-response/"> 响应请求 </a>
 * @author ahianzhang
 * @version 1.0
 * @date 2020/9/20 11:24 AM
 **/
public enum ErrorMessageEnum implements ErrorMessage{
    /**
     *参数缺失；不支持的参数；重复参数
     */
    INVALID_REQUEST("400","无效的请求"),
    /**
     * client 校验失败
     */
    INVALID_CLIENT("401","无效的 clientId 或 clientSecret"),

    DISABLED_CLIENT("400","应用已禁用"),

    WAS_CREATED_CLIENT("409","存在同名的应用"),
    /**
     * 授权类型不在
     * @see GrantType 中；
     * 回调地址与应用创建时填写的不符合
     */
    INVALID_GRANT("400","无效的授权"),

    UNSUPPORTED_GRANT("400","未支持的授权模式"),

    UNAUTHORIZED_CLIENT("400","授权模式与应用配置的授权模式不符"),

    INVALID_USER("400","用户名或密码错误" );


    private String resultCode;
    private String resultMessage;

    ErrorMessageEnum(String resultCode, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    @Override
    public String getResultCode() {
            return resultCode;
    }

    @Override
    public String getResultMessage() {
        return resultMessage;
    }
}
