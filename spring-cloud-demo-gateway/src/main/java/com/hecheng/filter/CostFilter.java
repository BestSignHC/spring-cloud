package com.hecheng.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class CostFilter implements GlobalFilter, Ordered{
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 这里是请求执行之前，放置请求到达时间
        exchange.getAttributes().put("REQUEST_START", System.currentTimeMillis());
        return chain.filter(exchange).then(
                // then为请求执行之后
                Mono.fromRunnable(() -> {
                    String requestPath = exchange.getRequest().getURI().getRawPath();
                    Long startTime = exchange.getAttribute("REQUEST_START");
                    if (startTime != null) {
                        System.out.printf("[%s] cost: %d%n", requestPath, System.currentTimeMillis() - startTime);
                    }
                })
        );
    }

    // 给过滤器设定优先级别的，值越大则优先级越低
    // 这里给一个很高的优先级
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }
}
