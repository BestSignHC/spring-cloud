package com.hecheng;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class TestController {

    @Value("${env:default value}")
    private String appEnv;

    //如果不存在会启动报错
    @Value("${not exists:default value}")
    private String notExists;

    @RequestMapping("/env")
    public String envAction() {
        return appEnv;
    }

    @RequestMapping("/not-exists")
    public String notFoundAction() {
        return notExists;
    }
}
