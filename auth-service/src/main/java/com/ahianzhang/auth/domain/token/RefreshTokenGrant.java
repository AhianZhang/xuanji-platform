package com.ahianzhang.auth.domain.token;///*
// * Copyright (c) 2020.  ahianzhang@gmail.com
// */
//
//
///**
// * @author ahianzhang
// * @version 1.0
// * @date 2020/9/25 12:17 PM
// **/
//public class RefreshTokenGrant implements Grant{
//
//    private final TokenService tokenService;
//
//    public RefreshTokenGrant(TokenService tokenService) {
//        this.tokenService = tokenService;
//    }
//
//    @Override
//    public void grants(String refreshToken,Integer refreshTokenSlidingExpiresIn) {
//        tokenService.refreshAccessToken(refreshToken,refreshTokenSlidingExpiresIn);
//    }
//
//    @Override
//    public void grants() {
//
//    }
//}
