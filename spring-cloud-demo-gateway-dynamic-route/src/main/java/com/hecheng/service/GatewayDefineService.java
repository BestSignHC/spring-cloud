package com.hecheng.service;

import com.alibaba.fastjson.JSON;
import com.hecheng.entity.GatewayDefine;
import com.hecheng.route_define.MysqlRouteDefineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

@Component
public class GatewayDefineService implements ApplicationEventPublisherAware {

    @Autowired
    private MysqlRouteDefineRepository mysqlRouteDefineRepository;

    private ApplicationEventPublisher publisher;

    @Override
    public List<GatewayDefine> findAll() {
        return gatewayDefineMapper.findAll();
    }

    @Override
    public String loadRouteDefinition() {
        List<GatewayDefine> allGatewayDefine = findAll();
        if (CollectionUtils.isEmpty(allGatewayDefine)) {
            return "no route define";
        }

        try {
            for (GatewayDefine define : allGatewayDefine) {
                RouteDefinition routeDefinition = new RouteDefinition();
                routeDefinition.setId(define.getId());
                routeDefinition.setUri(new URI(define.getUri()));

                routeDefinition.setPredicates(getPredicateDefinition(define));
                routeDefinition.setFilters(getFilterDefinition(define));

                routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe();
                publisher.publishEvent(new RefreshRoutesEvent(this));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return String.format("load route define err: %s", e.getMessage());
        }

        return "load route define success.";
    }

    public RouteDefinition save(RouteDefinition routeDefinition) {
        mysqlRouteDefineRepository.save(Mono.just(routeDefinition)).subscribe();
        publisher.publishEvent(new RefreshRoutesEvent(this));
        return routeDefinition;
    }

    public void deleteById(String id) {
        if
        mysqlRouteDefineRepository.delete(Mono.just(id)).subscribe();
        publisher.publishEvent(new RefreshRoutesEvent(this));
    }

    public boolean existsById(String id) throws NotFoundException {
        GatewayDefine entity = mysqlRouteDefineRepository.findOne(id);
        if (null == entity) {
            throw new NotFoundException("not found: " + id);
        }
        mysqlRouteDefineRepository.delete(Mono.just(id)).subscribe();
        return true;
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

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        publisher = applicationEventPublisher;
    }
}
