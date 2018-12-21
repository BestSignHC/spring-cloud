package com.hecheng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/consumer")
    public String consumer() {
        String response = restTemplate.getForObject("http://HC-EUREKA-CLIENT/ping", String.class);
        return response;
    }
}
