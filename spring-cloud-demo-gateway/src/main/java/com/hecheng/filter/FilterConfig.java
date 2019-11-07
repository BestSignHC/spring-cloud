package com.hecheng.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public CostFilter costFilter() {
        return new CostFilter();
    }

    @Bean
    public TokenGatewayFilterFactory tokenGatewayFilterFactory() {
        return new TokenGatewayFilterFactory();
    }
}
