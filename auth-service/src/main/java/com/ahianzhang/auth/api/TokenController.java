
package com.ahianzhang.auth.api;

import com.ahianzhang.auth.application.TokenManagerApplicationService;
import com.ahianzhang.auth.domain.token.AccessToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 2020/9/15 6:04 PM
 **/
@RestController
@RequestMapping("/oauth")
public class TokenController {
    private final TokenManagerApplicationService tokenService;

    public TokenController(TokenManagerApplicationService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/token")
    public AccessToken createToken(
            @RequestParam("grant_type") String grantType,
            @RequestParam(value = "username",required = false) String username,
            @RequestParam(value = "password",required = false) String password,
            @RequestParam("client_id") String clientId,
            @RequestParam(value = "client_secret",required = false) String clientSecret,
            @RequestParam(value = "scope",required = false) String scope,
            @RequestParam(value = "refresh_token",required = false) String refresh_token
    ){
      return tokenService.issueToken(grantType,clientId,scope,clientId,clientSecret,username,password,refresh_token);
    }

    @DeleteMapping("/token")
    public ResponseEntity<Boolean> revoke(@RequestParam("access_token") String accessToken){
      Boolean isRevoked = tokenService.revokeToken(accessToken);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(isRevoked);
    }


}
