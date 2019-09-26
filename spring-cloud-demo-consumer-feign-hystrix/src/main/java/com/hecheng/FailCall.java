package com.hecheng;

import org.springframework.stereotype.Component;

/**
 * 失败情况下的默认调用类
 */
@Component
public class FailCall implements ProducerInterface {
    @Override
    public String pingAction() {
        return "fail call: ping";
    }

    @Override
    public String pingWithNameAction(String name) {
        return "fail call: ping with name";
    }

    @Override
    public String pingUrlAction(String name) {
        return "fail call: ping rul";
    }

    @Override
    public String pingPostAction(String body) {
        return "fail call: ping post";
    }
}
