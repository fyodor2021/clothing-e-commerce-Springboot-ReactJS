package com.finefoods.apigateway.config;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.*;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import jakarta.servlet.http.Cookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private JwtService jwtService;
    private final WebClient.Builder webClientBuilder;

    @Qualifier("responseStatusExceptionHandler")
    @Autowired
    private WebExceptionHandler responseStatusExceptionHandler;

    public AuthenticationFilter(WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (routeValidator.isSecured.test(exchange.getRequest())) {
                MultiValueMap<String, HttpCookie> cookies = exchange.getRequest().getCookies();
                if (exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION) || cookies.containsKey("jwt")) {
                    if(exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                        String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                        if (authHeader != null && authHeader.startsWith("Bearer ")) {
                            authHeader = authHeader.substring(7);
                        }
                        try {
                            jwtService.validateToken(authHeader);
                        } catch (Exception e) {
                            if(cookies.containsKey("jwt")){
                                if(!validateJwtCookie(exchange, cookies)){
                                    return exchange.getResponse().setComplete();
                                }
                            }else{
                                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                                return exchange.getResponse().setComplete();
                            }
                        }
                    }else if(cookies.containsKey("jwt")){
                        if(!validateJwtCookie(exchange, cookies)){
                            return exchange.getResponse().setComplete();
                        }
                    }
                } else {
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }
            }
            return chain.filter(exchange);
        });
    }
    public static class Config {
    }
    private Boolean validateJwtCookie(ServerWebExchange exchange,  MultiValueMap<String, HttpCookie> cookies) {
        String jwtCookie = cookies.getFirst("jwt").getValue();
        if(jwtCookie != null){

        try{
            jwtService.validateToken(jwtCookie);
        }catch (Exception exception){
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            ResponseCookie cookie = ResponseCookie.from("jwt", "")
            .httpOnly(true)
            .secure(true)
            .path("/")
            .maxAge(0).build();
            exchange.getResponse().addCookie(cookie);
            return false;
        }
        }
        Claims  storedClaims = jwtService.extractAllClaims(jwtCookie);
        var jwtToken = jwtService.generateToken(storedClaims);
        if(jwtToken != null){
            try{
                exchange.getRequest()
                        .mutate()
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                        .build();
                return true;
            }catch (Exception exception){
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return false;
            }
        }
        return false;
    }
}