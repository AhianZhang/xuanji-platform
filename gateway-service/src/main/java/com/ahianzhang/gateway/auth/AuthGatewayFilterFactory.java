package com.ahianzhang.gateway.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;

import java.nio.charset.StandardCharsets;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 2020/9/24 3:09 PM
 **/
@Component
public class AuthGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthGatewayFilterFactory.Config> {

    @Value("${xuanji.gateway.auth.skip-address}")
    private String skipAuthAddress;

    private final StringRedisTemplate stringRedisTemplate;

    public AuthGatewayFilterFactory(StringRedisTemplate stringRedisTemplate){
        super(Config.class);
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String path = exchange.getRequest().getURI().getPath();
            if (skipAuthAddress.contains(path)){
                return chain.filter(exchange);
            }
            String token = exchange.getRequest().getHeaders().getFirst("token");
            if (StringUtils.isEmpty(token)){
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }

            String jwt = stringRedisTemplate.opsForValue().get("oat:"+token);
            if (StringUtils.isEmpty(jwt)){
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
                byte[] responseBody = "{\"errorMessage\": \"token 无效\"}".getBytes(StandardCharsets.UTF_8);
                DataBuffer buffer = response.bufferFactory().wrap(responseBody);
                return response.writeWith(Flux.just(buffer));
            }else {
                ServerHttpRequest req = exchange.getRequest().mutate().header("jwt", jwt).build();

                return chain.filter(exchange.mutate().request(req).build());
            }

        };
    }

    public static class Config {
    }
}
