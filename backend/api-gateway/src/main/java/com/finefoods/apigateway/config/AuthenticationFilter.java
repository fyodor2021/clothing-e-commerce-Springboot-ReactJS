package com.finefoods.apigateway.config;

import lombok.RequiredArgsConstructor;
import org.apache.hc.client5.http.auth.AuthStateCacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import java.time.Duration;
import java.util.Date;
import java.util.List;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private JwtService jwtService;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {

            if (routeValidator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                    return exchange.getResponse().setComplete();
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
                    jwtService.validateToken(authHeader);

                } catch (Exception e) {
                    System.out.println("token not valid");
                    throw new RuntimeException("token not valid");
                }
            } else {
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    if (!exchange.getRequest().getHeaders().containsKey("Cookie")) {
                        String guestToken = jwtService.generateGuestToken("guest");
                        exchange.getResponse().addCookie(ResponseCookie.from("SESSION", guestToken)
                                .httpOnly(false)
                                .path("/")
                                .maxAge(Duration.ofHours(2).toMillis()).build());
                        return chain.filter(exchange);
                    } else {
                        String cookie = exchange.getRequest().getHeaders().get("Cookie").get(0);
                        if (!cookie.contains(";")) {
                            String token = cookie.substring(8);
                            if (jwtService.validateGuestToken(token)) {
                                return chain.filter(exchange);
                            } else {
                                String guestToken = jwtService.generateGuestToken("guest");
                                exchange.getResponse().addCookie(ResponseCookie.from("SESSION", guestToken)
                                        .httpOnly(false)
                                        .path("/")
                                        .maxAge(Duration.ofHours(2).toMillis()).build());
                            }
                        }
                    }
                }
            }
            return chain.filter(exchange);
        }));


    }

    public static class Config {

    }
}
