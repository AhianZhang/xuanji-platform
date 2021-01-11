/*
 * Copyright (c) 2020.  ahianzhang@gmail.com
 */

package com.ahianzhang.auth.infrastructure.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 2020/9/20 12:33 PM
 **/
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BizException.class)
    @ResponseBody
    public ResultBody bizExceptionHandler(HttpServletRequest req, BizException e){
        return ResultBody.error(e.errorCode,e.errorMessage);
    }
}
