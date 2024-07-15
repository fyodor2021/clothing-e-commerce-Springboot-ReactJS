package com.finefoods.apigateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private JwtService jwtService;
    private final WebClient.Builder webClientBuilder;

    @Value("${cart-microservice.url}")
    private String cartUri;
    @Qualifier("responseStatusExceptionHandler")
    @Autowired
    private WebExceptionHandler responseStatusExceptionHandler;

    public AuthenticationFilter(WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            if (routeValidator.isSecured.test(exchange.getRequest())) {
                if (exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    authHeaderValidation(exchange);
                }
            } else {
                if (exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    authHeaderValidation(exchange);
                } else if (exchange.getRequest().getHeaders().containsKey("Cookie")) {
                    validateGuestToken(exchange);
                }
            }
            return chain.filter(exchange);
        }));


    }

    public static class Config {

    }

    private void createCart(String headerValue) {
        webClientBuilder.build()
                .post()
                .uri(cartUri)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(headerValue);
    }
    private void deleteCart(String headerValue) {
        webClientBuilder.build()
                .delete()
                .uri(cartUri + "/" + headerValue);
    }

    private void guestSetup(ServerWebExchange exchange) {
        String guestToken = jwtService.generateGuestToken("guest");
        exchange.getResponse().addCookie(ResponseCookie.from("JOSEDOR-SESSION", guestToken)
                .httpOnly(false)
                .path("/")
                .maxAge(Duration.ofHours(2).toMillis()).build());
        createCart(guestToken);
    }

    private void userSignOut(ServerWebExchange exchange) {
        exchange.getResponse().addCookie(ResponseCookie.from("SIGNOUT", "123")
                .httpOnly(false)
                .path("/")
                .maxAge(Duration.ofHours(2).toMillis()).build());
    }

    private void authHeaderValidation(ServerWebExchange exchange) {
        String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            authHeader = authHeader.substring(7);
        }
        try {
            jwtService.validateToken(authHeader);
        } catch (Exception e) {
            userSignOut(exchange);
        }
    }
    private void validateGuestToken(ServerWebExchange exchange) {
        String cookie = exchange.getRequest().getHeaders().get("Cookie").get(0);
        if (cookie.contains("JOSEDOR-SESSION")) {
            String[] cookies = cookie.split(";");
            String token;
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].contains("JOSEDOR-SESSION")) {
                    token = cookies[i].strip().substring(16);
                    try {
                        jwtService.validateGuestToken(token);
                    } catch (Exception e) {
                        deleteCart(token);
                        guestSetup(exchange);
                    }
                }
            }
        }else{
            guestSetup(exchange);
        }
    }
}
// if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
//        if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
//        exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
//                        return exchange.getResponse().setComplete();
//                    }
//String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
//                    if (authHeader != null && authHeader.startsWith("Bearer ")) {
//authHeader = authHeader.substring(7);
//                    }
//                            try {
//                            jwtService.validateToken(authHeader);
//                    } catch (Exception e) {
//        return userSignOut(exchange);
//                    }
//                            } else {
//                            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
//        if (!exchange.getRequest().getHeaders().containsKey("Cookie")) {
//guestSetup(exchange);
//                            return chain.filter(exchange);
//                        } else {
//String cookie = exchange.getRequest().getHeaders().get("Cookie").get(0);
//                            if (cookie.contains("JOSEDOR-SESSION")) {
//String[] cookies = cookie.split(";");
//String token;
//                                for (int i = 0; i < cookies.length; i++) {
//        if (cookies[i].contains("JOSEDOR-SESSION")) {
//token = cookies[i].strip().substring(16);
//                                        try {
//                                                jwtService.validateGuestToken(token);
//                                        } catch (Exception e) {
//guestSetup(exchange);
//                                        }
//                                                }
//                                                }
//                                                } else {
//guestSetup(exchange);
//                                return chain.filter(exchange);
//
//                            }
//                                    }
//                                    }
//                                    }
//                                    } else {
//                                    if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
//        exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
//                    return exchange.getResponse().setComplete();
//                }
//String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
//                if (authHeader != null && authHeader.startsWith("Bearer ")) {
//authHeader = authHeader.substring(7);
//                }
//                        try {
//                        jwtService.validateToken(authHeader);
//
//                } catch (Exception e) {
//        return userSignOut(exchange);
//                }
//                        }