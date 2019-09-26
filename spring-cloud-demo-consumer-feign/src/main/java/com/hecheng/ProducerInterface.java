package com.hecheng;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

// 指明对应的producer application name
@FeignClient(name = "hc-eureka-producer")
public interface ProducerInterface {

    @RequestMapping("/producer")
    public String pingAction();

    @GetMapping("/producerWithName")
    public String pingWithNameAction(@RequestParam(value = "name") String name);

    @GetMapping("/producer/{name}")
    public String pingUrlAction(@PathVariable String name);

    @PostMapping("/producerPost")
    public String pingPostAction(@RequestBody String body);
}
