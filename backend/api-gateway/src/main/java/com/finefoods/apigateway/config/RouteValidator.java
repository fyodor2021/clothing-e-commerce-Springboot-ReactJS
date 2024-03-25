package com.finefoods.apigateway.config;


import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;
@Component
public class RouteValidator {
    public static final List<String> openApiEndpoints = List.of(
            "/api/auth/register",
            "/api/auth/authenticate",
            "/api/product",
            "/api/product/{productId}",
            "/api/product/image/{productId}",
            "/api/review/product/",
            "/api/cart/add",
            "/api/cart/products",
            "/api/cart/product/inc",
            "/api/cart/product/dec",
            "/eureka"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
