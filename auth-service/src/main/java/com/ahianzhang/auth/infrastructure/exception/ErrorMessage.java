/*
 * Copyright (c) 2020.  ahianzhang@gmail.com
 */

package com.ahianzhang.auth.infrastructure.exception;

/**
 * @author ahianzhang
 */

public interface ErrorMessage {
    /**
     * 返回状态码
     * @return 状态码
     */
    String getResultCode();

    /**
     * 返回错误信息
     * @return 错误信息
     */
    String getResultMessage();



}
