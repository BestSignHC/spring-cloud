package com.hecheng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private RemoteInterface remoteInterface;

    @GetMapping("/consumer")
    public String index() {
        return remoteInterface.pingAction();
    }
}
