package com.hecheng;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

// 通过name，指明对应的producer application name
// 通过fallback，指定默认失败调用类
@FeignClient(name = "hc-eureka-producer", fallback = FailCall.class)
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
