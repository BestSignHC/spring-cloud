package com.hecheng.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

public class TokenGatewayFilterFactory extends AbstractGatewayFilterFactory<TokenGatewayFilterFactory.Config> {

    // 配置类（Config）中的属性
    private static final String KEY = "needToken";
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(KEY);
    }

    // 不能少
    public TokenGatewayFilterFactory() {
        super(Config.class);
        System.out.println("load TokenGatewayFilterFactory");
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new InnerFilter(config);
    }

    private class InnerFilter implements GatewayFilter, Ordered {

        private Config config;

        public InnerFilter(Config config) {
            this.config = config;
        }

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            // 根据配置决定，如果不要求token,则放行
            if (!config.isNeedToken()) {
                return chain.filter(exchange);
            }

            ServerHttpResponse response = exchange.getResponse();
            List<String> token = exchange.getRequest().getQueryParams().get("token");

            // 不含有token，不合法，直接拒绝
            if (CollectionUtils.isEmpty(token)) {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }

            return chain.filter(exchange);
        }

        @Override
        public int getOrder() {
            return Integer.MIN_VALUE;       // 最高优先级
        }
    }

    public static class Config {

        // 是否开启token认证
        private boolean needToken;

        public boolean isNeedToken() {
            return needToken;
        }

        public void setNeedToken(boolean needToken) {
            this.needToken = needToken;
        }
    }
}
