/*
 * Copyright (c) 2020.  ahianzhang@gmail.com
 */

package com.ahianzhang.auth.domain.token;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 2020/9/25 2:17 PM
 **/
public class GrantFactory {
    private static final Map<String,Grant> grants = new HashMap<>();
    static {
//        grants.put("refresh_token",new RefreshTokenGrant());
    }
    public static Grant getGrant(String type){
        if (type == null || type.isEmpty()){
            throw new IllegalArgumentException("授权类型不能为空！");
        }
        return grants.get(type);
    }
}
