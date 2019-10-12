package com.hecheng;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfig {

    @Bean
    public RouteLocator consumerRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(
                    r -> r.path("/c/**")
                    .filters(f -> f.stripPrefix(1))
                    .uri("lb://HC-CONSUMER-FEIGN-HYSTRIX")
                    .order(0)
                    .id("server-consumer")
                ).build();

    }
}
