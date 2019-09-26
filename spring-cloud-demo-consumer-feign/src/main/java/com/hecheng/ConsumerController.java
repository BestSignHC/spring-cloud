package com.hecheng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConsumerController {

    @Autowired
    private ProducerInterface producerInterface;

    @RequestMapping("/consumer")
    public String pingAction() {
        return producerInterface.pingAction();
    }

    @GetMapping("/consumerWithName")
    public String pingWithNameAction(@RequestParam(value = "name") String name) {
        return producerInterface.pingWithNameAction(name);
    }

    @GetMapping("/consumer/{name}")
    public String pingUrlAction(@PathVariable String name) {
        return producerInterface.pingUrlAction(name);
    }

    @PostMapping("/consumerPost")
    public String pingPostAction(@RequestBody String body) {
        return producerInterface.pingPostAction(body);
    }
}
