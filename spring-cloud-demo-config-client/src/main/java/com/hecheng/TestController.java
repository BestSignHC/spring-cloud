package com.hecheng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope  //这个配置项的话， 使用@value的需要加，不加获取不到更新后的值
public class TestController {

//    @Value("${env:default value}")
//    private String appEnv;
//
//    //如果不存在会启动报错
//    @Value("${not exists:default value}")
//    private String notExists;
//
//    @RequestMapping("/env")
//    public String envAction() {
//        return appEnv;
//    }
//
//    @RequestMapping("/not-exists")
//    public String notFoundAction() {
//        return notExists;
//    }

    @Value("${app.jdbc.url}")
    protected String jdbcUrl;

    @Autowired
    private Environment environment;

    // 通过这样写就不受@RefreshScope控制，可以获取到刷新后的值
    @RequestMapping("/getConfig/{configName}")
    public String getConfigAction(@PathVariable(name = "configName") String configName) {
        String value = environment.getProperty(configName, "not found");
        return String.format("configName: %s, value=%s", configName, value);
    }

    @RequestMapping("/getJdbcUrl")
    public String getJdbcUrlAction() {
        return jdbcUrl;
    }
}
