package com.hecheng;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "hc-eureka-producer")
public interface RemoteInterface {
    @GetMapping("/ping")
    String pingAction();

}
