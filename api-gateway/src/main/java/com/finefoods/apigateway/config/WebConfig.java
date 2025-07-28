package com.finefoods.apigateway.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.WebFluxConfigurer; // This is the correct interface
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.config.ResourceHandlerRegistry; // This is needed for addResourceHandlers in WebFluxConfigurer
import org.springframework.http.CacheControl;
import java.time.Duration;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
@Configuration
public class WebConfig implements WebFluxConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/", "classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/public/")
                .setCacheControl(CacheControl.noCache());

    }
    @Bean
    public RouterFunction<ServerResponse> spaFallbackRouter() {
        Resource indexHtml = new ClassPathResource("static/index.html");

        return RouterFunctions.route(GET("/"),
                request -> ServerResponse.ok().contentType(MediaType.TEXT_HTML).bodyValue(indexHtml)
        ).andRoute(request -> {
                    String path = request.path();
                    if (path.startsWith("/api/") ||
                            path.matches(".*\\.[a-zA-Z0-9]+$")) {
                        return false;
                    }
                    return true;
                }, request -> ServerResponse.ok().contentType(MediaType.TEXT_HTML).bodyValue(indexHtml)
        );
    }

}
