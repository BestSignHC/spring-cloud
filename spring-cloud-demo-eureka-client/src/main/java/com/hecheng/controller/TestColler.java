package com.hecheng.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestColler {

    @Value("${spring.application.name}")
    private String applicationName;

    @RequestMapping("/ping")
    public String pingAction() {
        return applicationName + " send msg: pong.";
    }
}
