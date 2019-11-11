package com.hecheng;

import com.alibaba.fastjson.JSON;
import com.hecheng.service.GatewayDefineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DynamicRouteController {

    @Autowired
    private GatewayDefineService gatewayDefineService;

    @PostMapping(value = "/routes/add")
    public String add(@RequestBody RouteDefinition routeDefinition) {
        System.out.println("request add route: " + JSON.toJSONString(routeDefinition));

        gatewayDefineService.save(routeDefinition);

        return "ok";
    }
}
