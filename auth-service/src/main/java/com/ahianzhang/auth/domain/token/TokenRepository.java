/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ahianzhang.auth.domain.token;

import java.util.Optional;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 2020/9/16 12:27 PM
 **/
public interface TokenRepository {
    Token save(Token token);

    Boolean revokeToken(String accessToken);

    void deleteExpireToken();


}
