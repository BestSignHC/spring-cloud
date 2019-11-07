package com.hecheng.fallback;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping("err")
    public Mono<String> errAction() {
        return Mono.just("err occur!");
    }
}
