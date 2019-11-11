package com.hecheng.route_define;

import com.alibaba.fastjson.JSON;
import com.hecheng.entity.GatewayDefine;
import com.hecheng.mapper.GatewayDefineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class MysqlRouteDefineRepository implements RouteDefinitionRepository {
    @Autowired
    private GatewayDefineMapper gatewayDefineMapper;

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        List<GatewayDefine> defines = gatewayDefineMapper.findAll();

        Map<String, RouteDefinition> routes = new LinkedHashMap<>();

        if (CollectionUtils.isEmpty(defines)) {
            return Flux.empty();
        }

        try {
            for (GatewayDefine define : defines) {
                RouteDefinition routeDefinition = new RouteDefinition();
                routeDefinition.setId(define.getId());
                routeDefinition.setUri(new URI(define.getUri()));

                routeDefinition.setPredicates(getPredicateDefinition(define));
                routeDefinition.setFilters(getFilterDefinition(define));

                routes.put(define.getId(), routeDefinition);
            }

            return Flux.fromIterable(routes.values());
        }
        catch (Exception e) {
            return Flux.empty();
        }
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return route.flatMap(r -> {
            try {
                if (null == r.getId() || "".equals(r.getId())) {
                    r.setId(UUID.randomUUID().toString());
                }
                GatewayDefine gatewayDefine = new GatewayDefine();
                gatewayDefine.setId(r.getId());
                gatewayDefine.setUri(r.getUri().toString());
                gatewayDefine.setPredicates(JSON.toJSONString(r.getPredicates()));
                gatewayDefine.setFilters(JSON.toJSONString(r.getFilters()));
                gatewayDefine.setName("name");
                gatewayDefineMapper.add(gatewayDefine);
                return Mono.empty();
            }
            catch (Exception e) {
                e.printStackTrace();
                return Mono.error(e);
            }
        });
    }

    public GatewayDefine findOne(String id) {
        return gatewayDefineMapper.findOne(id);
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.flatMap(id -> {
            gatewayDefineMapper.delete(id);
            return Mono.empty();
        });
    }

    public List<PredicateDefinition> getPredicateDefinition(GatewayDefine gatewayDefine) {
        String predicates = gatewayDefine.getPredicates();
        if (StringUtils.isEmpty(predicates)) {
            return null;
        }

        return JSON.parseArray(predicates, PredicateDefinition.class);
    }

    public List<FilterDefinition> getFilterDefinition(GatewayDefine gatewayDefine) {
        String filters = gatewayDefine.getFilters();
        if (StringUtils.isEmpty(filters)) {
            return null;
        }
        return JSON.parseArray(filters, FilterDefinition.class);
    }
}
