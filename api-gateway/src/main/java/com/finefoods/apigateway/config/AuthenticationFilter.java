package com.finefoods.apigateway.config;

import org.springframework.beans.factory.annotation.Autowired;
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
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private JwtService jwtService;
    private final WebClient.Builder webClientBuilder;

    @Value("${cart-microservice.url}")
    private String cartUri;

    public AuthenticationFilter(WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
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
                        exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                        return exchange.getResponse().setComplete();
                    }
                } else {
                    if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                        if (!exchange.getRequest().getHeaders().containsKey("Cookie")) {
                            String guestToken = jwtService.generateGuestToken("guest");
                            exchange.getResponse().addCookie(ResponseCookie.from("SESSION", guestToken)
                                    .httpOnly(false)
                                    .path("/")
                                   .maxAge(Duration.ofHours(2).toMillis()).build());
                            CreateCart(guestToken);
                            return chain.filter(exchange);
                        } else {
                            String cookie = exchange.getRequest().getHeaders().get("Cookie").get(0);
                            if (!cookie.contains(";")) {
                                String token = cookie.substring(8);
                                try{
                                    jwtService.validateGuestToken(token);
                                }catch (Exception e){
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
            }else{
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
                    exchange.getResponse().addCookie(ResponseCookie.from("SIGNOUT", "123")
                            .httpOnly(false)
                            .path("/")
                            .maxAge(Duration.ofHours(2).toMillis()).build());
                    exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                    return exchange.getResponse().setComplete();
                }
            }

            return chain.filter(exchange);
        }));


    }
//    public Mono<Void> getTokenAndValidate(ServerWebExchange exchange){
//
//         return Mono.empty();
//    }
    public static class Config {

    }
    private void CreateCart(String headerValue) {
        webClientBuilder.build()
                .post()
                .uri(cartUri)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(headerValue)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
