/*
 * Copyright (c) 2020.  ahianzhang@gmail.com
 */

package com.ahianzhang.auth.infrastructure.exception;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 2020/9/20 12:28 PM
 **/
public class ResultBody {
    private String code;
    private String message;
    private Object result;

    public ResultBody() {
    }

    public ResultBody(String code, String message, Object result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public static ResultBody error(ErrorMessage errorMessage){
        ResultBody resultBody = new ResultBody();
        resultBody.setCode(errorMessage.getResultCode());
        resultBody.setMessage(errorMessage.getResultMessage());
        resultBody.setResult(null);
        return resultBody;
    }
    public static ResultBody error(String errorCode ,String errorMessage){
        ResultBody resultBody = new ResultBody();
        resultBody.setCode(errorCode);
        resultBody.setMessage(errorMessage);
        resultBody.setResult(null);
        return resultBody;
    }
}
