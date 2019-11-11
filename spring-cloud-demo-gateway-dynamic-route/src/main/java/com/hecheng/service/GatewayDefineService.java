package com.hecheng.service;

import com.hecheng.entity.GatewayDefine;
import com.hecheng.route_define.MysqlRouteDefineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class GatewayDefineService implements ApplicationEventPublisherAware {

    @Autowired
    private MysqlRouteDefineRepository mysqlRouteDefineRepository;

    private ApplicationEventPublisher publisher;

    public Flux<RouteDefinition> getRouteDefinitions() {
        return mysqlRouteDefineRepository.getRouteDefinitions();
    }


    public RouteDefinition save(RouteDefinition routeDefinition) {
        mysqlRouteDefineRepository.save(Mono.just(routeDefinition)).subscribe();
        publisher.publishEvent(new RefreshRoutesEvent(this));
        return routeDefinition;
    }

    public void deleteById(String id) {
        mysqlRouteDefineRepository.delete(Mono.just(id)).subscribe();
        publisher.publishEvent(new RefreshRoutesEvent(this));
    }

    public boolean existsById(String id) throws NotFoundException {
        GatewayDefine entity = mysqlRouteDefineRepository.findOne(id);
        if (null == entity) {
            throw new NotFoundException("not found: " + id);
        }
        return true;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        publisher = applicationEventPublisher;
    }
}
