/*
 * Copyright (c) 2020.  ahianzhang@gmail.com
 */

package com.ahianzhang.auth.infrastructure.exception;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 2020/9/20 12:09 PM
 **/
public class BizException extends RuntimeException{
    protected String errorCode;
    protected String errorMessage;

    public BizException() {
        super();
    }
    public BizException(ErrorMessage errorMessage){
        super(errorMessage.getResultMessage());
        this.errorCode = errorMessage.getResultCode();
        this.errorMessage = errorMessage.getResultMessage();
    }

    public BizException(ErrorMessage errorMessage,Throwable cause){
        super(errorMessage.getResultMessage(),cause);
        this.errorCode = errorMessage.getResultCode();
        this.errorMessage = errorMessage.getResultMessage();
    }

    public BizException(String errorMessage) {
        super(errorMessage);

        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
