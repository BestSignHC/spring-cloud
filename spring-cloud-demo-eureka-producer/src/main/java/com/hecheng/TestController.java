package com.hecheng;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${producer.instance.no}")
    private String instanceNo;

    @RequestMapping("/producer")
    public String pingAction() {
        return String.format("%s[%s] send msg: %d", applicationName, instanceNo, System.currentTimeMillis());
    }

    @GetMapping("/producerWithName")
    public String pingWithNameAction(@RequestParam(value = "name") String name) {
        return String.format("%s[%s] send msg to %s : %d", applicationName, instanceNo, name, System.currentTimeMillis());
    }

    @GetMapping("/producer/{name}")
    public String pingUrlAction(@PathVariable String name) {
        return String.format("%s[%s] send msg to %s : %d", applicationName, instanceNo, name, System.currentTimeMillis());
    }

    @PostMapping("/producerPost")
    public String pingPostAction(@RequestBody String body) {
        return String.format("%s[%s] send msg to %s : %d", applicationName, instanceNo, body, System.currentTimeMillis());
    }
}
