package com.hecheng;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${spring.application.name}")
    private String applicationName;

    @RequestMapping("/producer")
    public String pingAction() {
        return applicationName + " send msg: ." + System.currentTimeMillis();
    }
}
